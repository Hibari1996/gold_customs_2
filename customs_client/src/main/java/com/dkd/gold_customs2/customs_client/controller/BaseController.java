package com.dkd.gold_customs2.customs_client.controller;

import com.alibaba.fastjson.JSONObject;
import com.dkd.gold_customs2.customs_client.config.TokenUtil;
import com.dkd.gold_customs2.customs_client.entity.user.UserInfo;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseController {
    //返回收集的错误信息
    public Object returnJsonByMsg(BindingResult bindingResult){
        JSONObject json = new JSONObject();
        if(bindingResult!=null){
            String message="";
            List<ObjectError> ls=bindingResult.getAllErrors();
            for (int i = 0; i < ls.size(); i++) {
                message +=ls.get(i).getDefaultMessage()+'\n';
            }
           json.put("message", message);
        }
        return null;
    }
    public  boolean checktoken(HttpServletRequest request,String token){
        List<String> tokenList=(List<String>) request.getSession().getAttribute("token");
        if(tokenList != null){
            if(tokenList.contains(token)){
           tokenList.remove(token);
             return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    protected Map<String,Object> getObjectBySession(HttpServletRequest request, String key){
        Map<String,Object> map = new HashMap<String,Object>();
        HttpSession session = request.getSession();  //获取session
        Object obj = null;
        if("userInfo".equals(key)){
            obj = (UserInfo)session.getAttribute("user");
            map.put("userInfo",obj);
        }else if("token".equals(key)){
            Object object = request.getSession().getAttribute("tokenMap");
            if(object == null){//无token创建token
                Map<String,String> tokenMap = new HashMap<String,String> ();
                String sessionId = session.getId();
                String token = TokenUtil.getInstance().makeToken();//创建令牌
                tokenMap.put(sessionId,token);
                map.put("token",token);
            }else{//有token返回token
                Map<String,String> tokenMap = (Map<String, String>) object;
                String sessionId = session.getId();
                String token = tokenMap.get(sessionId);
                map.put("token",token);
            }
        }else if("removeToken".equals(key)){//移除token
            request.getSession().setAttribute("tokenMap",null);
        }else{}

        return map;
    }

}
