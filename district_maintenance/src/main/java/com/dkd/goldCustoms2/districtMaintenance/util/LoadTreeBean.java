package com.dkd.goldCustoms2.districtMaintenance.util;


import com.dkd.goldCustoms2.districtMaintenance.domain.vo.DepartmentInfo;
import com.dkd.goldCustoms2.districtMaintenance.domain.vo.UserInfo;
import com.dkd.goldCustoms2.districtMaintenance.h4aservices.TreeBean;

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
