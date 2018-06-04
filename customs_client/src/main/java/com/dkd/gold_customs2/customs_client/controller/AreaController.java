package com.dkd.gold_customs2.customs_client.controller;


import com.alibaba.fastjson.JSONObject;
import com.dkd.gold_customs2.customs_client.config.TokenUtil;
import com.dkd.gold_customs2.customs_client.entity.special_regulatory_area.DistrictArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class AreaController extends BaseController{
    @Value("${cst.canic.address}")
    private String address;
    @Value("${cst.canic.port}")
    private String port;
    @Value("${cst.canic.cheaktoken}")
    private String cheaktoken;
    @Autowired
    private RestTemplate restTemplate;

    //获取关区区域创建TOKEN
    @RequestMapping("/area/create")
    public Object create(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();  //创建session
        String token = TokenUtil.getInstance().makeToken();//创建令牌
        List<String> list=(List<String>) request.getSession().getAttribute("token");
        if(null !=list){
            list.add(token);
            session.setAttribute("token",list);
        }else{
            List<String> tokenList= Collections.synchronizedList(new ArrayList<String>());//创建tokenList
            tokenList.add(token);
            session.setAttribute("token",tokenList);
        }
        JSONObject json = new JSONObject();
        json.put("token",token);
        return json;
    }
    //关区区域创建
    @RequestMapping(value = "/area/save",produces = "application/json")
    public Object save(HttpServletRequest request, @ModelAttribute("form") @Valid DistrictArea districtArea, BindingResult bindingResult){
        boolean checkToken = true;
        if(bindingResult.getErrorCount() == 0){
            if(cheaktoken.equals("1")){
                checkToken=checktoken(request,districtArea.getToken());
            }
            if(checkToken == true){
                String url="http://"+address+":"+port+"/core/area/save";
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<DistrictArea> entity = new HttpEntity<DistrictArea>(districtArea, headers);
                /***替换原来传值方式改为JSON传
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("districtArea",districtArea);
                Map<String,Object> sessionMap = (Map<String,Object>)getObjectBySession(request,"userInfo");
                map.put("userInfo",sessionMap.get("userInfo"));
                String postjson=ObjectToJson.ObjListToJson((Map<String,Object>)map);
                JSONObject jsonMerge = JSONObject.parseObject(postjson);
                HttpEntity<JSONObject> entity = new HttpEntity<JSONObject>(jsonMerge, headers);
                */
                ResponseEntity<String> responseEntity=restTemplate.postForEntity(url,entity,String.class);
                return responseEntity.getBody();
            }else{
                return null;
            }
             }else{
         return returnJsonByMsg(bindingResult);
        }
    }
    //获取关区区域编辑信息
    @RequestMapping("/area/edit/{id}")
    public Object edit(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id){
        HttpSession session = request.getSession();  //创建session
        String token = TokenUtil.getInstance().makeToken();//创建令牌
        List<String> checkToken=(List<String>) request.getSession().getAttribute("token");
        if(null != checkToken){
            checkToken.add(token);
            session.setAttribute("token",checkToken);
        }else{
            List<String> tokenList= Collections.synchronizedList(new ArrayList<String>());//创建tokenList
            tokenList.add(token);
            session.setAttribute("token",tokenList);
        }
        String url="http://"+address+":"+port+"/core/area/edit/";
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(url+id,String.class);
        JSONObject  districtArea = JSONObject.parseObject(responseEntity.getBody());
        districtArea.put("token",token);
//        DistrictArea dir=JSONObject.toJavaObject(districtArea, DistrictArea.class);
//        JSONObject json = new JSONObject();
//        json.put("districtArea",dir);
//        json.put("token",token);
        return districtArea;
    }
    //关区区域编辑修改
    @RequestMapping("/area/update")
    public Object update(HttpServletRequest request, @RequestBody @Valid DistrictArea districtArea, BindingResult bindingResult){
        boolean checkToken = true;
        if(bindingResult.getErrorCount() == 0){
            if(cheaktoken.equals("1")){
                checkToken=checktoken(request,districtArea.getToken());
            }
            if(checkToken == true){
                String url="http://"+address+":"+port+"/core/area/update";
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<DistrictArea> entity = new HttpEntity<DistrictArea>(districtArea, headers);
                ResponseEntity<String> responseEntity=restTemplate.postForEntity(url,entity,String.class);
                return responseEntity.getBody();
            }else{
                return null;
            }
        }else{
            return returnJsonByMsg(bindingResult);
        }
    }
    //关区区域列表
    @RequestMapping("/area/list")
    public Object list(int page,int size,@RequestBody DistrictArea districtArea){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //DistrictArea districtArea=new DistrictArea();
        HttpEntity<DistrictArea> entity = new HttpEntity<DistrictArea>(districtArea, headers);
        String url="http://"+address+":"+port+"/core/area/list?page="+page+"&size="+size;
        ResponseEntity<String> responseEntity=restTemplate.postForEntity(url,entity,String.class);
        return responseEntity.getBody();
    }
    //判断重复
    @RequestMapping("/area/valid")
    public Object valid(@RequestBody DistrictArea districtArea){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DistrictArea> entity = new HttpEntity<DistrictArea>(districtArea, headers);
        String url="http://"+address+":"+port+"/core/area/valid";
        ResponseEntity<String> responseEntity=restTemplate.postForEntity(url,entity,String.class);
        return responseEntity.getBody();

    }

    //获取科室列表
    @RequestMapping("/area/areaList")
    public Object getList(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url="http://"+address+":"+port+"/core/relation/area/list";
        ResponseEntity<String> responseEntity=restTemplate.postForEntity(url,null,String.class);
        return responseEntity.getBody();
    }

    @RequestMapping("/area/loginJmp")
    public String loginJmp(){
        return "page/nonprotect/areaAssociate";
    }

//
//    @RequestMapping("/area/test")
//    public Object test(HttpServletRequest request){
//         /*替换原来传值方式改为JSON传*/
//         Map<String,Object> map = new HashMap<String,Object>();
//         map.put("districtArea",getMap().get("area"));
//         Map<String,Object> sessionMap = (Map<String,Object>)getObjectBySession(request,"userInfo");
//         map.put("userInfo",sessionMap.get("userInfo"));
//         String postjson=ObjectToJson.ObjListToJson((Map<String,Object>)map);
//         JSONObject jsonMerge = JSONObject.parseObject(postjson);
//
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<JSONObject> entity = new HttpEntity<JSONObject>(jsonMerge, headers);
//        String url="http://"+address+":"+port+"/core/relation/area/list";
//        ResponseEntity<String> responseEntity=restTemplate.postForEntity(url,entity,String.class);
//        return responseEntity.getBody();
//    }
//
//    public static Map<String,Object> getMap(){
//        UserInfo user00=new UserInfo();
//        user00.setCode("27");
//        user00.setDeptName("smart");
//        user00.setDeptId("man");
//        user00.setName("man");
//
//        DistrictArea area = new DistrictArea();
//        area.setOptCode("11111111");
//        area.setAreaName("22222222");
//        Map<String,Object> map = new HashMap<String,Object>();
//        //ArrayList<Object> list=new ArrayList<Object>();
//        map.put("userInfo",user00);
//        map.put("area",area);
//        return map;
//    }

}
