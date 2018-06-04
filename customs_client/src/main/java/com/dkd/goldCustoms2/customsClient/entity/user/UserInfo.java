package com.dkd.goldCustoms2.customsClient.entity.user;

import java.io.Serializable;

/**
 * @Description: 
 * @Author: zhengxudong
 * @Date: 2018/5/8  17:53
 */
public class UserInfo implements Serializable {

    public UserInfo(){
        super();
    }

    public UserInfo(String id, String deptId){
        this.id = id;
        this.deptId = deptId;
    }

    public UserInfo(String id, String name, String code, String deptId, String deptName) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.deptId = deptId;
        this.deptName = deptName;
    }

    private String viewGuid; //view_guid 视图ID

    private String id;//user_guid

    private String name;//display_name

    private String code;//person_id

    private String deptId;//parent_guid

    private String deptName;//all_path_name  天津海关\天津物流园海关\张妍

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getViewGuid() {
        return viewGuid;
    }

    public void setViewGuid(String viewGuid) {
        this.viewGuid = viewGuid;
    }
}
