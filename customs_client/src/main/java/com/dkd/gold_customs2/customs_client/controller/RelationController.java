package com.dkd.gold_customs2.customs_client.controller;

import com.alibaba.fastjson.JSONObject;
import com.dkd.gold_customs2.customs_client.config.TokenUtil;
import com.dkd.gold_customs2.customs_client.entity.special_regulatory_area.DistrictAreaRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
public class RelationController extends BaseController{
    @Value("${cst.canic.address}")
    private String address;
    @Value("${cst.canic.port}")
    private String port;
    @Value("${cst.canic.cheaktoken}")
    private String cheaktoken;
    @Autowired
    private RestTemplate restTemplate;
    //科室关联信息创建TOKEN
    @RequestMapping("/relation/dept/create")
    public Object deptCreate(HttpServletRequest request, HttpServletResponse response,Integer districtAreaId) {
        HttpSession session = request.getSession();  //创建session
        String token = TokenUtil.getInstance().makeToken();//创建令牌
        List<String> CheckTokenList = (List<String>) request.getSession().getAttribute("token");
        if (CheckTokenList != null) {
            CheckTokenList.add(token);
            session.setAttribute("token", CheckTokenList);
        } else {
            List<String> tokenList = Collections.synchronizedList(new ArrayList<String>());//创建tokenList
            tokenList.add(token);
            session.setAttribute("token", tokenList);
        }
        session.setMaxInactiveInterval(900);
//        String url="http://"+address+":"+port+"/relation/dept/create?districtAreaId="+districtAreaId;
        String url="http://"+address+":"+port+"/core/area/edit/"+districtAreaId;//update
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(url,String.class);
        JSONObject  districtAreaRelation = JSONObject.parseObject(responseEntity.getBody());
        JSONObject json = new JSONObject();
        json.put("districtAreaRelation",districtAreaRelation);
        json.put("token",token);
        return json;
    }
    //科室关联信息创建
    @RequestMapping("/relation/dept/save")
    public String deptSave(HttpServletRequest request,String []deptIds,
                           Integer districtAreaId,
                           String token){
        boolean checkToken = true;
        if(cheaktoken.equals("1")){
            checkToken=checktoken(request,token);
        }
        if(checkToken == true){
            String url="http://"+address+":"+port
                    +"/core/relation/dept/save?districtAreaId={districtAreaId}&deptIds={deptIds}";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, Object> uriVariables = new HashMap<String, Object>();
            uriVariables.put("districtAreaId",districtAreaId);
            uriVariables.put("deptIds",deptIds);
            ResponseEntity<String> responseEntity=restTemplate.postForEntity(url,headers,String.class,uriVariables);
            return responseEntity.getBody();
        }else{
            return null;
        }
    }

    //科室树
    @RequestMapping("/relation/deptTree")
    public Object deptTree(String id){
        String url="http://"+address+":"+port+"/core/relation/deptTree";
        if(id != null){
            url += "?id="+id;
        }
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(url,String.class);
        return responseEntity.getBody();
    }

    //科室关联信息展示
    @RequestMapping("/relation/deptList")
    public Object deptList(Integer districtAreaId, String deptName,DistrictAreaRelation districtAreaRelation){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DistrictAreaRelation> entity = new HttpEntity<DistrictAreaRelation>(districtAreaRelation, headers);
        String url="http://"+address+":"+port+"/core/relation/deptList?districtAreaId="+districtAreaId+"&deptName="+deptName;
        ResponseEntity<String> responseEntity=restTemplate.postForEntity(url,entity,String.class);
        return responseEntity.getBody();
    }
    //人员关联信息创建
    @RequestMapping("/relation/user/create")
    public Object userCreate(HttpServletRequest request, HttpServletResponse response,Integer districtAreaId){
        HttpSession session = request.getSession();  //创建session
        String token = TokenUtil.getInstance().makeToken();//创建令牌
        List<String> CheckTokenList = (List<String>) request.getSession().getAttribute("token");
        if (CheckTokenList != null) {
            CheckTokenList.add(token);
            session.setAttribute("token", CheckTokenList);
        } else {
            List<String> tokenList = Collections.synchronizedList(new ArrayList<String>());//创建tokenList
            tokenList.add(token);
            session.setAttribute("token", tokenList);
        }
        session.setMaxInactiveInterval(900);
//        String url="http://"+address+":"+port+"/relation/user/create?districtAreaId="+districtAreaId;
        String url="http://"+address+":"+port+"/core/area/edit/"+districtAreaId;//update
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(url,String.class);
        JSONObject  districtAreaRelation = JSONObject.parseObject(responseEntity.getBody());
        JSONObject json = new JSONObject();
        json.put("districtAreaRelation",districtAreaRelation);
        json.put("token",token);
        return json;
    }
    //人员关联信息保存
    @RequestMapping("/relation/user/save")
    public String userSave(HttpServletRequest request,String []userIds,int districtAreaId,String token){
        boolean checkToken = true;
        if(cheaktoken.equals("1")){
            checkToken=checktoken(request,token);
        }
        if(checkToken == true){
            String url="http://"+address+":"+port
                    +"/core/relation/user/save?districtAreaId={districtAreaId}&userIds={userIds}";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, Object> uriVariables = new HashMap<String, Object>();
            uriVariables.put("districtAreaId",districtAreaId);
            uriVariables.put("userIds",userIds);
            ResponseEntity<String> responseEntity=restTemplate.postForEntity(url,headers,String.class,uriVariables);
            return responseEntity.getBody();
        }else{
            return null;
        }
    }
    //人员关联信息展示
    @RequestMapping("/relation/userList")
    public Object userList(int districtAreaId,String userName,String userCode){
        String url="http://"+address+":"+port+"/core/relation/userList?districtAreaId="+districtAreaId+"&userName="+userName+"&userCode="+userCode;
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(url,String.class);
        return responseEntity.getBody();
    }
    //三统一人员查询
    @RequestMapping("/relation/user/query")
    public Object  userQuery(String id){
        String url="http://"+address+":"+port+"/core/relation/user/query";
        if(id != null){
            url += "?id="+id;
        }
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(url,String.class);
        return responseEntity.getBody();
    }
    //科室删除
    @RequestMapping("/relation/deptList/delete/{id}")
    public Object deptListDelete(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id){
        String url="http://"+address+":"+port+"/core/relation/deptList/delete/";
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(url+id,String.class);
        return responseEntity.getBody();
    }
    //人员删除
    @RequestMapping("/relation/user/delete/{id}")
    public Object userDelete(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id){
        String url="http://"+address+":"+port+"/core/relation/user/delete/";
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(url+id,String.class);
        return responseEntity.getBody();
    }
}
