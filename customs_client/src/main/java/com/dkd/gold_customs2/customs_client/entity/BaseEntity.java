package com.dkd.gold_customs2.customs_client.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;



public class BaseEntity implements Serializable {


    protected Long version;

    /**
     * 录入人id
     */

    protected String createId;

    /**
     * 录入人姓名
     */
    protected String createName;

    /**
     * 录入日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy/MM/dd HH:mm:ss")
    protected Date createDate;

    /**
     * 最后编辑人ID
     */

    protected String optCode;


    protected String optName;

    /**
     * 最后修改时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy/MM/dd HH:mm:ss")
    protected Date optDate;


    protected Long delFlag;
    protected String token;
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

    public Long getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Long delFlag) {
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
}
