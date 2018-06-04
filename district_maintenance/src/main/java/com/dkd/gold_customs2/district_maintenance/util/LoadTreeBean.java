package com.dkd.gold_customs2.district_maintenance.util;


import com.dkd.gold_customs2.district_maintenance.domain.vo.DepartmentInfo;
import com.dkd.gold_customs2.district_maintenance.domain.vo.UserInfo;
import com.dkd.gold_customs2.district_maintenance.h4aservices.TreeBean;

import java.util.concurrent.ConcurrentHashMap;

public class LoadTreeBean {

    private static ConcurrentHashMap<String,TreeBean> map = new ConcurrentHashMap<String,TreeBean> ();

    public static void put(String key, TreeBean treeBean){
        map.put(key,treeBean);
    }

    public static TreeBean get(String id){
        return map.get(id);
    }

    public static UserInfo getUserInfo(String id) {
        return ConvertUtil.convertUserInfo(get(id));
    }

    public static DepartmentInfo getDepartmentInfo(String id) {
        return ConvertUtil.convertDepartmentInfo(get(id));
    }
}
