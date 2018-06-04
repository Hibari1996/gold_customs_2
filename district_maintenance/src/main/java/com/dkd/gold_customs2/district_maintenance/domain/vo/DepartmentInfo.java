package com.dkd.gold_customs2.district_maintenance.domain.vo;

/**
 * @Description: 
 * @Author: zhengxudong
 * @Date: 2018/5/8  18:46
 */
public class DepartmentInfo {

    private String viewGuid; //view_guid

    private String id;//org_guid

    private String name;//display_name

    private String parentId;//parent_guid 上级部门ID

    private String OrgPath;//all_path_name

    public DepartmentInfo() {
        super();
    }

    public DepartmentInfo(String id, String name, String parentId, String orgPath) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        OrgPath = orgPath;
    }

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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getOrgPath() {
        return OrgPath;
    }

    public void setOrgPath(String orgPath) {
        OrgPath = orgPath;
    }

    public String getViewGuid() {
        return viewGuid;
    }

    public void setViewGuid(String viewGuid) {
        this.viewGuid = viewGuid;
    }
}
