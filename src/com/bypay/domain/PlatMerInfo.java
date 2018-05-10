package com.bypay.domain;

public class PlatMerInfo {
	private String platMerId;	//	PLAT_MER _ID	BIGINT		1	否	自增长
	private String platMerName;	//	PLAT_MER_NAME	VARCHAR	60	否	平台商工商注册名
	private String merRegNo;	//	MER_REG_NO	VARCHAR	20	否	工商注册号
	private String merTaxNo;	//	MER_TAX_NO	VARCHAR	20	否	税务登记号
	private String legalPerson;	//	LEGAL_PERSON	VARCHAR	60	否	法人姓名
	private String legalIdcard;	//	LEGAL_IDCARD	VARCHAR	20	否	法人身份证号
	private String contactor;	//	CONTACTOR	VARCHAR	60	是	联系人
	private String contactPhone;	//	CONTACT_PHONE	VARCHAR	20	是	联系电话
	private String contactEmail;	//	CONTACT_EMAIL	VARCHAR	100	是	联系邮箱
	private String contactAddr;	//	CONTACT_ADDR	VARCHAR	200	是	联系地址
	private String createTime;	//	CREATE_TIME	VARCHAR	20	否	创建时间
	private String status;	//	STATUS	VARCHAR	1	是	状态   0：未审核   1：正在使用   2：暂停服务
	private String remark;	//	REMARK	VARCHAR	100	是	备注
	private String reserved;	//	RESERVED	VARCHAR	50	是	扩展
	private String logo;	//	LOGO	VARCHAR	50	是	Logo图,用于管理平台显示
	private String color;	//	COLOR	VARCHAR	10	是	色调,用于管理平台显示

	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPlatMerId() {
		return platMerId;
	}
	public void setPlatMerId(String platMerId) {
		this.platMerId = platMerId;
	}
	public String getPlatMerName() {
		return platMerName;
	}
	public void setPlatMerName(String platMerName) {
		this.platMerName = platMerName;
	}
	public String getMerRegNo() {
		return merRegNo;
	}
	public void setMerRegNo(String merRegNo) {
		this.merRegNo = merRegNo;
	}
	public String getMerTaxNo() {
		return merTaxNo;
	}
	public void setMerTaxNo(String merTaxNo) {
		this.merTaxNo = merTaxNo;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getLegalIdcard() {
		return legalIdcard;
	}
	public void setLegalIdcard(String legalIdcard) {
		this.legalIdcard = legalIdcard;
	}
	public String getContactor() {
		return contactor;
	}
	public void setContactor(String contactor) {
		this.contactor = contactor;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getContactAddr() {
		return contactAddr;
	}
	public void setContactAddr(String contactAddr) {
		this.contactAddr = contactAddr;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

}
