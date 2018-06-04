package com.dkd.gold_customs2.customs_client.entity.special_regulatory_area;


import com.dkd.gold_customs2.customs_client.entity.BaseEntity;

public class DistrictAreaRelation extends BaseEntity {

    private Long id;

    /**
     * 类型（科室1  人员2）
     */

    private Long relationType;

    /**
     * 关区代码
     */

    private String areaCode;

    /**
     * 部门ID
     */

    private String departmentId;

    /**
     * 组织架构路径
     */

    private String orgPath;

    /**
     * 人员id
     */

    private String userId;

    /**
     * 人员名称
     */

    private String userName;

    /**
     * 人员工号
     */

    private String userCode;

    /**
     * 状态
     */

    private String status;


    /**
     * 关区区域ID
     */
    private DistrictArea  districtArea;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRelationType() {
        return relationType;
    }

    public void setRelationType(Long relationType) {
        this.relationType = relationType;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getOrgPath() {
        return orgPath;
    }

    public void setOrgPath(String orgPath) {
        this.orgPath = orgPath;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DistrictArea getDistrictArea() {
        return districtArea;
    }

    public void setDistrictArea(DistrictArea districtArea) {
        this.districtArea = districtArea;
    }
}
