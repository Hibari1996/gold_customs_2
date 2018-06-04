package com.dkd.gold_customs2.district_maintenance.domain.entity;

import com.dkd.gold_customs2.district_maintenance.domain.vo.UserInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class BaseEntity implements Serializable {

    public static final Integer REGULAR_STATUS = 0;
    public static final Integer DELETE_STATUS = 1;

    @Version
    protected Long version;

    /**
     * 录入人id
     */
    @Column(name="CREATE_ID",nullable = false,length = 100,updatable = true)
    protected String createId;

    /**
     * 录入人姓名
     */
    @Column(name="CREATE_NAME",nullable = false,length = 100,updatable = true)
    protected String createName;

    /**
     * 录入日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy/MM/dd HH:mm:ss")
    @Column(name = "CREATE_DATE",nullable = false,updatable = true)
    @DateTimeFormat(pattern = "yyyy/MM/dd HH-mm-ss")
    protected Date createDate;

    /**
     * 最后编辑人ID
     */
    @Column(name="OPT_CODE",nullable = false,length = 100)
    protected String optCode;


    @Column(name="OPT_NAME",nullable = false,length = 100)
    protected String optName;

    /**
     * 最后修改时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy/MM/dd HH:mm:ss")
    @Column(name = "OPT_DATE",nullable = false)
    @DateTimeFormat(pattern = "yyyy/MM/dd HH-mm-ss")
    protected Date optDate;

    @Column(name="DEL_FLAG",nullable = false)
    protected Integer delFlag;


    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getOptCode() {
        return optCode;
    }

    public void setOptCode(String optCode) {
        this.optCode = optCode;
    }

    public Date getOptDate() {
        return optDate;
    }

    public void setOptDate(Date optDate) {
        this.optDate = optDate;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getOptName() {
        return optName;
    }

    public void setOptName(String optName) {
        this.optName = optName;
    }

    public void setOptInfo(UserInfo userInfo) {

        if (getCreateId() == null || StringUtils.isEmpty(getCreateId())){
            setCreateId(userInfo.getId());
            setCreateName(userInfo.getName());
            setCreateDate(new Date());
        }
        setOptCode(userInfo.getId());
        setOptName(userInfo.getName());
        setOptDate(new Date());
    }
}
