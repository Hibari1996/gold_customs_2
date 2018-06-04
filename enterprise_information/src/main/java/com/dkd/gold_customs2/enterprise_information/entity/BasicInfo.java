package com.dkd.gold_customs2.enterprise_information.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 企业基本信息
 *
 * @author Harry
 * @date 2018/5/29
 */


@Entity
@Table(name = "M_BASIC_INFO")
public class BasicInfo implements Serializable {

	private static final long serialVersionUID = 5765537212522444006L;

	/**
	 * 企业编码
	 */
	@Id
	@Column(name = "ENTERPRISE_ID",length = 20)
	private String id;


	/**
	 * 企业简称
	 */
	@NotNull(message = "企业简称不能为空")
	@Column(name = "SHOT_NAME",nullable = false,length = 100)
	private String shotName;


	/**
	 * 企业全称
	 */
	@NotNull(message = "企业全称不能为空")
	@Column(name = "FULL_NAME",nullable = false,length = 100)
	private String fullName;


	/**
	 * 企业英文名称
	 */
	@NotNull(message = "企业英文名称不能为空")
	@Column(name = "EN_NAME",nullable = false,length = 100)
	private String enName;


	/**
	 * 企业中文地址
	 */
	@Column(name = "CH_ADDRESS",length = 100,updatable = false)
	private String chAddress;


	/**
	 * 企业监管级别
	 */
	@Column(name = "SUPERVISE_LV",length = 40,updatable = false)
	private String superviseLV;


	/**
	 * 注册海关
	 */
	@Column(name = "REGISTERED_CUSTOMS",length = 40,updatable = false)
	private String registeredCustoms;


	/**
	 * 企业邮编
	 */
	@Column(name = "ZIP_CODE",length = 20,updatable = false)
	private String zipCode;


	/**
	 * 注册日期
	 */
	@Column(name = "REGIST_DATE",updatable = false)
	private Date registDate;


	/**
	 * 营业执照号
	 */
	@Column(name = "LICENSE",length = 20,updatable = false)
	private String license;


	/**
	 * 企业行业种类
	 */
	@Column(name = "CATEGORY",length = 20,updatable = false)
	private String category;


	/**
	 * 企业联系人
	 */
	@Column(name = "CONTACTS",length = 40,updatable = false)
	private String contacts;


	/**
	 * 联系人电话
	 */
	@Column(name = "TEL",length = 20,updatable = false)
	private String tel;


	/**
	 * 法人代表
	 */
	@Column(name = "LEGAL",length = 40,updatable = false)
	private String legal;


	/**
	 * 法人电话
	 */
	@Column(name = "LEGAL_TEL",length = 20,updatable = false)
	private String legalTel;


	/**
	 * 注册资金
	 */
	@Column(name = "SCOPE",precision = 20, scale = 4,updatable = false)
	private BigDecimal scope;


	/**
	 * 主要产品
	 */
	@Column(name = "PRODUCTS",length = 200,updatable = false)
	private String products;


	/**
	 * 下载日期
	 */
	@Column(name = "DOWNLOAD_DATE",updatable = false)
	private Date downloadDate;


	/**
	 * 下载人编号
	 */
	@Column(name = "DOWNLOADER",length = 20,updatable = false)
	private String downloader;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShotName() {
		return shotName;
	}

	public void setShotName(String shotName) {
		this.shotName = shotName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getChAddress() {
		return chAddress;
	}

	public void setChAddress(String chAddress) {
		this.chAddress = chAddress;
	}

	public String getSuperviseLV() {
		return superviseLV;
	}

	public void setSuperviseLV(String superviseLV) {
		this.superviseLV = superviseLV;
	}

	public String getRegisteredCustoms() {
		return registeredCustoms;
	}

	public void setRegisteredCustoms(String registeredCustoms) {
		this.registeredCustoms = registeredCustoms;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}

	public String getLegalTel() {
		return legalTel;
	}

	public void setLegalTel(String legalTel) {
		this.legalTel = legalTel;
	}

	public BigDecimal getScope() {
		return scope;
	}

	public void setScope(BigDecimal scope) {
		this.scope = scope;
	}

	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}

	public Date getDownloadDate() {
		return downloadDate;
	}

	public void setDownloadDate(Date downloadDate) {
		this.downloadDate = downloadDate;
	}

	public String getDownloader() {
		return downloader;
	}

	public void setDownloader(String downloader) {
		this.downloader = downloader;
	}
}
