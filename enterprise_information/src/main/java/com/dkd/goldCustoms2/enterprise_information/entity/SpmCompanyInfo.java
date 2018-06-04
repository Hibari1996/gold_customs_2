package com.dkd.goldCustoms2.enterprise_information.entity;

import com.dkd.goldCustoms2.enterprise_information.utils.ToStringUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 服务平台_账册表表实体
 */

@Entity
@Table(name="SPM_COM_INFO_FB")
public class SpmCompanyInfo implements Serializable {

	/**
	 * 服务平台_账册表实体序列号
	 */
	
	private static final long serialVersionUID = 6039284595697338766L;

	/**
	 *	id
	 */
	@Id
	@Column(nullable=false,length=50,name="ID")
	private String id;

	/**
	 *	企业编码
	 */
	@Column(length=10,name="COMPANY_CODE")
	private String companyCode;

	/**
	 *	账册信息
	 */
	@Column(length=50,name="BOOK_NUMBER")
	private String bookNumber;

	/**
	 *	海关编码
	 */
	@Column(length=10,name="CUSTOMS_CODE_C")
	private String customsCodeC;

	/**
	 *	区域编码
	 */
	@Column(length=10,name="AREA_CODE")
	private String areaCode;

	/**
	 *	海关名称
	 */
	@Column(length=255,name="CUSTOMS_NAME_C")
	private String customsNameC;

	/**
	 * 区域名称
	 */
	@Column(length=255,name="AREA_NAME")
	private String areaName ;

	/**
	 *	创建时间
	 */
	@Column(name="CREATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	//以下属性为大写的字段都是来自H2010webservice接口。

	/**
	 *	企业注册号
	 */
	@Column(length=255,name="TRADE_CO")
	@JsonProperty
	private String TRADE_CO;

	/**
	 *	未知
	 */
	@Column(length=255,name="PRE_TRADE_CO")
	@JsonProperty
	private String PRE_TRADE_CO;

	/**
	 *	注册日期
	 */
	@Column(length=255,name="RG_DATE")
	@JsonProperty
	private String RG_DATE;

	/**
	 *	获取企业注册名称(全)
	 */
	@Column(length=255,name="FULL_NAME")
	@JsonProperty
	private String FULL_NAME;

	/**
	 *	注册海关代码
	 */
	@Column(length=255,name="CUSTOMS_CODE")
	@JsonProperty
	private String CUSTOMS_CODE;

	/**
	 *	获取企业注册名称(简)
	 */
	@Column(length=255,name="REG_CO")
	@JsonProperty
	private String REG_CO;

	/**
	 *	企业监管级别
	 */
	@Column(length=255,name="CO_CLASS")
	@JsonProperty
	private String CO_CLASS;

	/**
	 *	中文地址
	 */
	@Column(length=255,name="ADDR_CO")
	@JsonProperty
	private String ADDR_CO;

	/**
	 *	企业邮编
	 */
	@Column(length=255,name="MAIL_CO")
	@JsonProperty
	private String MAIL_CO;

	/**
	 *	企业英文名称
	 */
	@Column(length=255,name="EN_FULL_CO")
	@JsonProperty
	private String EN_FULL_CO;

	/**
	 *	企业英文地址
	 */
	@Column(length=255,name="EN_ADDR_CO")
	@JsonProperty
	private String EN_ADDR_CO;

	/**
	 *	到位资金
	 */
	@Column(length=255,name="ACT_FUND")
	@JsonProperty
	private String ACT_FUND;

	/**
	 *	保证金额
	 */
	@Column(length=255,name="CHK_EARNEST_MON")
	@JsonProperty
	private String CHK_EARNEST_MON;

	/**
	 *	内销比率
	 */
	@Column(length=255,name="IN_RATIO")
	@JsonProperty
	private String IN_RATIO;

	/**
	 *	批准机关
	 */
	@Column(length=255,name="APPR_DEP")
	@JsonProperty
	private String APPR_DEP;

	/**
	 *	批准文号
	 */
	@Column(length=255,name="APPR_ID")
	@JsonProperty
	private String APPR_ID;

	/**
	 *	企业生产类型
	 */
	@Column(length=255,name="CO_TYPE")
	@JsonProperty
	private String CO_TYPE;

	/**
	 *	开户银行
	 */
	@Column(length=255,name="ACCO_BANK")
	@JsonProperty
	private String ACCO_BANK;

	/**
	 *	ACCO_NO
	 */
	@Column(length=255,name="ACCO_NO")
	@JsonProperty
	private String ACCO_NO;

	/**
	 *	行业种类
	 */
	@Column(length=255,name="BUSI_TYPE")
	@JsonProperty
	private String BUSI_TYPE;

	/**
	 *	法人代表
	 */
	@Column(length=255,name="LAW_MAN")
	@JsonProperty
	private String LAW_MAN;

	/**
	 *	居留证号码
	 */
	@Column(length=255,name="ID_NUMBER")
	@JsonProperty
	private String ID_NUMBER;

	/**
	 *	法人电话
	 */
	@Column(length=255,name="LAW_MAN_TEL")
	@JsonProperty
	private String LAW_MAN_TEL;

	/**
	 *	联系人
	 */
	//
	@Column(length=255,name="CONTAC_CO")
	@JsonProperty
	private String CONTAC_CO;

	/**
	 *	联系人电话
	 */
	@Column(length=255,name="TEL_CO")
	@JsonProperty
	private String TEL_CO;

	/**
	 *	报关类别
	 */
	@Column(length=255,name="BROKER_TYPE")
	@JsonProperty
	private String BROKER_TYPE;

	/**
	 *	注册资本(万)
	 */
	@Column(length=255,name="RG_FUND")
	@JsonProperty
	private String RG_FUND;

	/**
	 *	注册资金币制
	 */
	@Column(length=255,name="CURR_CODE")
	@JsonProperty
	private String CURR_CODE;

	/**
	 * 未知
	 */
	@Column(length=255,name="INV_FUND_T")
	@JsonProperty
	private String INV_FUND_T;

	/**
	 *	税务登记号
	 */
	@Column(length=255,name="TAXY_RG_NO")
	@JsonProperty
	private String TAXY_RG_NO;

	/**
	 *	营业执照号
	 */
	@Column(length=255,name="LICENSE_ID")
	@JsonProperty
	private String LICENSE_ID;

	/**
	 * 未知
	 */
	@Column(length=255,name="COP_GB_CODE")
	@JsonProperty
	private String COP_GB_CODE;

	/**
	 *	未知
	 */
	@Column(length=255,name="VALID_DATE")
	@JsonProperty
	private String VALID_DATE;

	/**
	 *	企业进出口代码
	 */
	@Column(length=255,name="COP_IO_CODE")
	@JsonProperty
	private String COP_IO_CODE;

	/**
	 *	企业通关期限
	 */
	@Column(length=255,name="COP_END_DATE")
	@JsonProperty
	private String COP_END_DATE;

	/**
	 *	企业经营范围
	 */
	@Column(length=255,name="COP_RANGE")
	@JsonProperty
	private String COP_RANGE;

	/**
	 *	未知
	 */
	@Column(length=255,name="CREDIT_MAR")
	@JsonProperty
	private String CREDIT_MAR;

	/**
	 *	企业评分
	 */
	@Column(length=255,name="EXAM_SCORE")
	@JsonProperty
	private String EXAM_SCORE;

	/**
	 *	年审日期
	 */
	@Column(length=255,name="CHK_DATE")
	@JsonProperty
	private String CHK_DATE;

	/**
	 *	走私次数
	 */
	@Column(length=255,name="BREAK_LAW_TIME1")
	@JsonProperty
	private String BREAK_LAW_TIME1;

	/**
	 *	违规次数
	 */
	@Column(length=255,name="BREAK_LAW_TIME2")
	@JsonProperty
	private String BREAK_LAW_TIME2;

	/**
	 *	稽查次数
	 */
	@Column(length=255,name="INSPECT_TIME")
	@JsonProperty
	private String INSPECT_TIME;

	/**
	 *	特殊企业标志
	 */
	@Column(length=255,name="SPE_CO")
	@JsonProperty
	private String SPE_CO;

	/**
	 *	经营地区范围
	 */
	@Column(length=255,name="TRADE_AREA")
	@JsonProperty
	private String TRADE_AREA;

	/**
	 *	主要产品
	 */
	@Column(length=255,name="MAIN_PRO")
	@JsonProperty
	private String MAIN_PRO;

	/**
	 *	免税额(万美元)
	 */
	@Column(length=255,name="DUTY_FREE_AMT")
	@JsonProperty
	private String DUTY_FREE_AMT;

	/**
	 *	企业标志
	 */
	@Column(length=255,name="COP_FLAG")
	@JsonProperty
	private String COP_FLAG;

	/**
	 *	备注
	 */
	@Column(length=255,name="COP_NOTE")
	@JsonProperty
	private String COP_NOTE;

	/**
	 *	企业最后修改日期
	 */
	@Column(length=255,name="COP_MODIFY_DATE")
	@JsonProperty
	private String COP_MODIFY_DATE;

	/**
	 *	企业备案管理id
	 */
	@Column(length=255,name="COMPANY_DECLARE_ID")
	private String companyDeclareId;

	/**
	 *	同步到服务平台时间
	 */
	@Column(name="SYNC_TO_SPM_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date syncToSpmTime ;

	//以下三条属性不会同步到数据库，只做和前台交互的时候将值返回

	/**
	 *	审批状态
	 */
	@Transient //
	private Integer status;

	/**
	 *	审批结果
	 */
	@Transient
	private String statusMessage;

	/**
	 *	拒绝原因
	 */
	@Transient
	private String rejectReason;
	
	@JsonIgnore
	public String getCREDIT_MAR() {
		return CREDIT_MAR;
	}
	@JsonIgnore
	public void setCREDIT_MAR(String cREDIT_MAR) {
		CREDIT_MAR = cREDIT_MAR;
	}
	@JsonIgnore
	public String getEXAM_SCORE() {
		return EXAM_SCORE;
	}
	@JsonIgnore
	public void setEXAM_SCORE(String eXAM_SCORE) {
		EXAM_SCORE = eXAM_SCORE;
	}
	@JsonIgnore
	public String getCHK_DATE() {
		return CHK_DATE;
	}
	@JsonIgnore
	public void setCHK_DATE(String cHK_DATE) {
		CHK_DATE = cHK_DATE;
	}
	@JsonIgnore
	public String getBREAK_LAW_TIME1() {
		return BREAK_LAW_TIME1;
	}
	@JsonIgnore
	public void setBREAK_LAW_TIME1(String bREAK_LAW_TIME1) {
		BREAK_LAW_TIME1 = bREAK_LAW_TIME1;
	}
	@JsonIgnore
	public String getBREAK_LAW_TIME2() {
		return BREAK_LAW_TIME2;
	}
	@JsonIgnore
	public void setBREAK_LAW_TIME2(String bREAK_LAW_TIME2) {
		BREAK_LAW_TIME2 = bREAK_LAW_TIME2;
	}
	@JsonIgnore
	public String getINSPECT_TIME() {
		return INSPECT_TIME;
	}
	@JsonIgnore
	public void setINSPECT_TIME(String iNSPECT_TIME) {
		INSPECT_TIME = iNSPECT_TIME;
	}
	@JsonIgnore
	public String getSPE_CO() {
		return SPE_CO;
	}
	@JsonIgnore
	public void setSPE_CO(String sPE_CO) {
		SPE_CO = sPE_CO;
	}
	@JsonIgnore
	public String getTRADE_AREA() {
		return TRADE_AREA;
	}
	@JsonIgnore
	public void setTRADE_AREA(String tRADE_AREA) {
		TRADE_AREA = tRADE_AREA;
	}
	@JsonIgnore
	public String getMAIN_PRO() {
		return MAIN_PRO;
	}
	@JsonIgnore
	public void setMAIN_PRO(String mAIN_PRO) {
		MAIN_PRO = mAIN_PRO;
	}
	@JsonIgnore
	public String getDUTY_FREE_AMT() {
		return DUTY_FREE_AMT;
	}
	@JsonIgnore
	public void setDUTY_FREE_AMT(String dUTY_FREE_AMT) {
		DUTY_FREE_AMT = dUTY_FREE_AMT;
	}
	@JsonIgnore
	public String getCOP_FLAG() {
		return COP_FLAG;
	}
	@JsonIgnore
	public void setCOP_FLAG(String cOP_FLAG) {
		COP_FLAG = cOP_FLAG;
	}
	@JsonIgnore
	public String getCOP_NOTE() {
		return COP_NOTE;
	}
	@JsonIgnore
	public void setCOP_NOTE(String cOP_NOTE) {
		COP_NOTE = cOP_NOTE;
	}
	@JsonIgnore
	public String getCOP_MODIFY_DATE() {
		return COP_MODIFY_DATE;
	}
	@JsonIgnore
	public void setCOP_MODIFY_DATE(String cOP_MODIFY_DATE) {
		COP_MODIFY_DATE = cOP_MODIFY_DATE;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getSyncToSpmTime() {
		return syncToSpmTime;
	}

	public void setSyncToSpmTime(Date syncToSpmTime) {
		this.syncToSpmTime = syncToSpmTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(String bookNumber) {
		this.bookNumber = bookNumber;
	}



	public String getCustomsCodeC() {
		return customsCodeC;
	}

	public void setCustomsCodeC(String customsCodeC) {
		this.customsCodeC = customsCodeC;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@JsonIgnore
	public String getTRADE_CO() {
		return TRADE_CO;
	}
	@JsonIgnore
	public void setTRADE_CO(String tRADE_CO) {
		TRADE_CO = tRADE_CO;
	}
	@JsonIgnore
	public String getPRE_TRADE_CO() {
		return PRE_TRADE_CO;
	}
	@JsonIgnore
	public void setPRE_TRADE_CO(String pRE_TRADE_CO) {
		PRE_TRADE_CO = pRE_TRADE_CO;
	}
	@JsonIgnore
	public String getRG_DATE() {
		return RG_DATE;
	}
	@JsonIgnore
	public void setRG_DATE(String rG_DATE) {
		RG_DATE = rG_DATE;
	}
	@JsonIgnore
	public String getFULL_NAME() {
		return FULL_NAME;
	}
	@JsonIgnore
	public void setFULL_NAME(String fULL_NAME) {
		FULL_NAME = fULL_NAME;
	}

	
	@JsonIgnore
	public String getCUSTOMS_CODE() {
		return CUSTOMS_CODE;
	}
	@JsonIgnore
	public void setCUSTOMS_CODE(String cUSTOMS_CODE) {
		CUSTOMS_CODE = cUSTOMS_CODE;
	}
	@JsonIgnore
	public String getREG_CO() {
		return REG_CO;
	}
	@JsonIgnore
	public void setREG_CO(String rEG_CO) {
		REG_CO = rEG_CO;
	}
	@JsonIgnore
	public String getCO_CLASS() {
		return CO_CLASS;
	}
	@JsonIgnore
	public void setCO_CLASS(String cO_CLASS) {
		CO_CLASS = cO_CLASS;
	}
	@JsonIgnore
	public String getADDR_CO() {
		return ADDR_CO;
	}
	@JsonIgnore
	public void setADDR_CO(String aDDR_CO) {
		ADDR_CO = aDDR_CO;
	}
	@JsonIgnore
	public String getMAIL_CO() {
		return MAIL_CO;
	}
	@JsonIgnore
	public void setMAIL_CO(String mAIL_CO) {
		MAIL_CO = mAIL_CO;
	}
	@JsonIgnore
	public String getEN_FULL_CO() {
		return EN_FULL_CO;
	}
	@JsonIgnore
	public void setEN_FULL_CO(String eN_FULL_CO) {
		EN_FULL_CO = eN_FULL_CO;
	}
	@JsonIgnore
	public String getEN_ADDR_CO() {
		return EN_ADDR_CO;
	}
	@JsonIgnore
	public void setEN_ADDR_CO(String eN_ADDR_CO) {
		EN_ADDR_CO = eN_ADDR_CO;
	}
	@JsonIgnore
	public String getACT_FUND() {
		return ACT_FUND;
	}
	@JsonIgnore
	public void setACT_FUND(String aCT_FUND) {
		ACT_FUND = aCT_FUND;
	}
	@JsonIgnore
	public String getCHK_EARNEST_MON() {
		return CHK_EARNEST_MON;
	}
	@JsonIgnore
	public void setCHK_EARNEST_MON(String cHK_EARNEST_MON) {
		CHK_EARNEST_MON = cHK_EARNEST_MON;
	}
	@JsonIgnore
	public String getIN_RATIO() {
		return IN_RATIO;
	}
	@JsonIgnore
	public void setIN_RATIO(String iN_RATIO) {
		IN_RATIO = iN_RATIO;
	}
	@JsonIgnore
	public String getAPPR_DEP() {
		return APPR_DEP;
	}
	@JsonIgnore
	public void setAPPR_DEP(String aPPR_DEP) {
		APPR_DEP = aPPR_DEP;
	}
	@JsonIgnore
	public String getAPPR_ID() {
		return APPR_ID;
	}
	@JsonIgnore
	public void setAPPR_ID(String aPPR_ID) {
		APPR_ID = aPPR_ID;
	}
	@JsonIgnore
	public String getCO_TYPE() {
		return CO_TYPE;
	}
	@JsonIgnore
	public void setCO_TYPE(String cO_TYPE) {
		CO_TYPE = cO_TYPE;
	}
	@JsonIgnore
	public String getACCO_BANK() {
		return ACCO_BANK;
	}
	@JsonIgnore
	public void setACCO_BANK(String aCCO_BANK) {
		ACCO_BANK = aCCO_BANK;
	}
	@JsonIgnore
	public String getACCO_NO() {
		return ACCO_NO;
	}
	@JsonIgnore
	public void setACCO_NO(String aCCO_NO) {
		ACCO_NO = aCCO_NO;
	}
	@JsonIgnore
	public String getBUSI_TYPE() {
		return BUSI_TYPE;
	}
	@JsonIgnore
	public void setBUSI_TYPE(String bUSI_TYPE) {
		BUSI_TYPE = bUSI_TYPE;
	}
	@JsonIgnore
	public String getLAW_MAN() {
		return LAW_MAN;
	}
	@JsonIgnore
	public void setLAW_MAN(String lAW_MAN) {
		LAW_MAN = lAW_MAN;
	}



	public String getID_NUMBER() {
		return ID_NUMBER;
	}

	public void setID_NUMBER(String iD_NUMBER) {
		ID_NUMBER = iD_NUMBER;
	}

	public String getLAW_MAN_TEL() {
		return LAW_MAN_TEL;
	}

	public void setLAW_MAN_TEL(String lAW_MAN_TEL) {
		LAW_MAN_TEL = lAW_MAN_TEL;
	}

	public String getCONTAC_CO() {
		return CONTAC_CO;
	}

	public void setCONTAC_CO(String cONTAC_CO) {
		CONTAC_CO = cONTAC_CO;
	}

	public String getTEL_CO() {
		return TEL_CO;
	}

	public void setTEL_CO(String tEL_CO) {
		TEL_CO = tEL_CO;
	}

	public String getBROKER_TYPE() {
		return BROKER_TYPE;
	}

	public void setBROKER_TYPE(String bROKER_TYPE) {
		BROKER_TYPE = bROKER_TYPE;
	}

	public String getRG_FUND() {
		return RG_FUND;
	}

	public void setRG_FUND(String rG_FUND) {
		RG_FUND = rG_FUND;
	}

	public String getCURR_CODE() {
		return CURR_CODE;
	}

	public void setCURR_CODE(String cURR_CODE) {
		CURR_CODE = cURR_CODE;
	}

	public String getINV_FUND_T() {
		return INV_FUND_T;
	}

	public void setINV_FUND_T(String iNV_FUND_T) {
		INV_FUND_T = iNV_FUND_T;
	}

	public String getTAXY_RG_NO() {
		return TAXY_RG_NO;
	}

	public void setTAXY_RG_NO(String tAXY_RG_NO) {
		TAXY_RG_NO = tAXY_RG_NO;
	}

	public String getLICENSE_ID() {
		return LICENSE_ID;
	}

	public void setLICENSE_ID(String lICENSE_ID) {
		LICENSE_ID = lICENSE_ID;
	}

	public String getCOP_GB_CODE() {
		return COP_GB_CODE;
	}

	public void setCOP_GB_CODE(String cOP_GB_CODE) {
		COP_GB_CODE = cOP_GB_CODE;
	}

	public String getVALID_DATE() {
		return VALID_DATE;
	}

	public void setVALID_DATE(String vALID_DATE) {
		VALID_DATE = vALID_DATE;
	}

	public String getCOP_IO_CODE() {
		return COP_IO_CODE;
	}

	public void setCOP_IO_CODE(String cOP_IO_CODE) {
		COP_IO_CODE = cOP_IO_CODE;
	}

	public String getCOP_END_DATE() {
		return COP_END_DATE;
	}

	public void setCOP_END_DATE(String cOP_END_DATE) {
		COP_END_DATE = cOP_END_DATE;
	}

	public String getCOP_RANGE() {
		return COP_RANGE;
	}

	public void setCOP_RANGE(String cOP_RANGE) {
		COP_RANGE = cOP_RANGE;
	}

	public String getCompanyDeclareId() {
		return companyDeclareId;
	}

	public void setCompanyDeclareId(String companyDeclareId) {
		this.companyDeclareId = companyDeclareId;
	}

	
	public String getCustomsNameC() {
		return customsNameC;
	}

	public void setCustomsNameC(String customsNameC) {
		this.customsNameC = customsNameC;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	@Override
	public String toString() {
		try {
			return ToStringUtils.getToString(this);
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}
}