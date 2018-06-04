package com.dkd.goldCustoms2.districtMaintenance.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmptyUtil {

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if ((obj instanceof List)) {
            return ((List) obj).size() == 0;
        }
        if ((obj instanceof String)) {
            return ((String) obj).trim().equals("");
        }
        return false;
    }

    public static boolean isNotEmpty(Object obj) {
        return isEmpty(obj);
    }

    //""字段转null
    public static void Model2Model(Object obj){
        //String nameVlues="";
        //得到class
        //Class cls = obj.getClass();
        //得到所有属性
        //Field[] fields = cls.getDeclaredFields();

        List<Field> fieldList = new ArrayList<>() ;
        Class cls = obj.getClass();
        while (cls != null) {//当父类为null的时候说明到达了最上层的父类(Object类).
            fieldList.addAll(Arrays.asList(cls .getDeclaredFields()));
            cls = cls.getSuperclass(); //得到父类,然后赋给自己
        }
        for (int i=0;i<fieldList.size();i++){//遍历
            try {
                //得到属性
                Field field = fieldList.get(i);
                //打开私有访问
                field.setAccessible(true);
                //获取属性
                String name = field.getName();
                String type = field.getType().toString();//得到此属性的类型
                if (type.endsWith("String")) {
                    Object value = field.get(obj);
                    if (isEmpty(value)) {
                        field.set(obj, null);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
