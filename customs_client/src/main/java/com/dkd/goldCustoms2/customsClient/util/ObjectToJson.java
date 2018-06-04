package com.dkd.goldCustoms2.customsClient.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

public class ObjectToJson {

    public  static String ObjListToJson(Map<String,Object> map){
        StringBuilder build=new StringBuilder();
        //迭代器
        Iterator iterator = map.entrySet().iterator();
        build.append("{");
        String separate="";
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Object key = entry.getKey();

            Object value = entry.getValue();
            build.append(separate);
            build.append("\""+key+"\":");
            //object转json字符串
            String str=ObjToJson(value);
            build.append(str);
            separate=",";
        }
        build.append("}");
        return build.toString();
    }

    @SuppressWarnings("unchecked")
    public  static String ObjToJson(Object obj){
        StringBuilder build=new StringBuilder();
        build.append("{");
        @SuppressWarnings("rawtypes")
        Class cla=null;
        try {
            //反射加载类
            cla=Class.forName(obj.getClass().getName());
        } catch (ClassNotFoundException e) {
            System.out.println(obj.getClass().toString().concat(" 未找到这个类"));
            e.printStackTrace();
            return null;
        }

        StringBuffer methodname=new StringBuffer();
        //获取java类的变量
        Field[] fields=cla.getDeclaredFields();
        String separate="";
        for(Field temp:fields){
            build.append(separate);
            build.append("\"");
            build.append(temp.getName());
            build.append("\":");

            methodname.append("get");
            methodname.append(temp.getName().substring(0,1).toUpperCase());
            methodname.append(temp.getName().substring(1));

            build.append("\"");
            Method method=null;
            try {
                //获取java的get方法
                method=cla.getMethod(methodname.toString());
            } catch (NoSuchMethodException | SecurityException e) {
                methodname.setLength(0);
                e.printStackTrace();
            }
            try {
                //执行get方法，获取变量参数的直。
                build.append(method.invoke(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            build.append("\"");
            methodname.setLength(0);
            separate=",";
        }

        build.append("}");
        return build.toString();
    }

//    public static void main(String[] args){
//        //数据构建
//        Map<String,Object> map =getMap();
//        //数据转换
//        String listjson=ObjectToJson.ObjListToJson((Map<String,Object>)map);
//        System.out.println(listjson);
//    }
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