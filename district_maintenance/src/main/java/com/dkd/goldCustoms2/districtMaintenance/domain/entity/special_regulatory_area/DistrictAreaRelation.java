package com.dkd.goldCustoms2.districtMaintenance.domain.entity.special_regulatory_area;

import com.dkd.goldCustoms2.districtMaintenance.domain.entity.BaseEntity;
import com.dkd.goldCustoms2.districtMaintenance.domain.vo.DepartmentInfo;
import com.dkd.goldCustoms2.districtMaintenance.domain.vo.UserInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="M_DISTRICTAREARELATION")
public class DistrictAreaRelation extends BaseEntity {

    /**
     * 部门关联
     */
    public static final Integer DEPT_RELATION = 1;
    /**
     * 用户关联
     */
    public static final Integer USER_RELATION = 2;


    @Id
    @SequenceGenerator(name = "areaIdGen", sequenceName = "M_DISTRICTAREA_AREA_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "areaIdGen")
    @Column(name = "RELATION_ID")
    private Long id;

    /**
     * 类型（科室1  人员2）
     */
    @Column(name = "RELATION_TYPE",nullable = false)
    private Integer relationType;

    /**
     * 关区代码
     */
    @Column(name = "AREA_CODE",nullable = false,length = 100)
    private String areaCode;

    /**
     * 区域代码
     */
    @Column(name = "REGION_CODE",nullable = false,length = 100)
    private String regionCode;

    /**
     * 部门ID
     */
    @Column(name = "DEPARTMENT_ID",nullable = true)
    private String departmentId;

    /**
     * 组织架构路径
     */
    @Column(name = "ORG_PATH",nullable = true)
    private String orgPath;

    /**
     * 人员id
     */
    @Column(name = "USER_ID",nullable = true)
    private String userId;

    /**
     * 人员名称
     */
    @Column(name = "USER_NAME",nullable = true,length = 100)
    private String userName;

    /**
     * 人员工号
     */
    @Column(name = "USER_CODE",nullable = true,length = 100)
    private String userCode;


    /**
     * 关区区域ID
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DISTRICTAREA_ID")
    private DistrictArea  districtArea;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRelationType() {
        return relationType;
    }

    public void setRelationType(Integer relationType) {
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

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public DistrictArea getDistrictArea() {
        return districtArea;
    }

    public void setDistrictArea(DistrictArea districtArea) {
        this.setAreaCode(districtArea.getAreaCode());
        this.setRegionCode(districtArea.getRegionCode());
        this.districtArea = districtArea;
    }


    public void setDepartmentInfo(DepartmentInfo dept) {
        this.setRelationType(DistrictAreaRelation.DEPT_RELATION);
        this.setDepartmentId(dept.getId());
        this.setOrgPath(dept.getOrgPath());
    }

    public void setDeptAndUserInfo(UserInfo user){
        this.setRelationType(DistrictAreaRelation.USER_RELATION);
        this.setDepartmentId(user.getDeptId());
        this.setOrgPath(user.getDeptName());
        this.setUserId(user.getId());
        this.setUserCode(user.getCode());
        this.setUserName(user.getName());
    }
}
