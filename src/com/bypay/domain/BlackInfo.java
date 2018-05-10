package com.bypay.domain;

public class BlackInfo {
	private String id;			//	ID	BIGINT		自增长ID
	private String createTime;	//	CREATE_TIME		创建时间
	private String subMerId;	//	SUB_MER_ID		子商户号外键
	private String cardNo;		//	CARD_NO		卡号
	private String cardType;	//	CARD_TYPE		卡类型
	private String realName;	//	REAL_NAME		姓名	
	private String idNum;		//	ID_NUM		证件号	
	private String phone;		//	PHONE		手机号
	private String level;		//	LEVEL		黑名单级别
	private String blackType;	//	BLACK_TYPE		是	黑名单类型
	private String status;		//	STATUS		当前状态
	private String terminalId;	//	TERMINAL_ID		终端号码
	private String reserved1;	//	RESERVED1		扩展1
	private String reserved2;	//	RESERVED2		扩展2
	private String reserved3;	//	RESERVED3		扩展3
	private String remark;		//	REMARK		备注
	
	private String subMerName;	//子商户名称
	
	public String getSubMerName() {
		return subMerName;
	}
	public void setSubMerName(String subMerName) {
		this.subMerName = subMerName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getSubMerId() {
		return subMerId;
	}
	public void setSubMerId(String subMerId) {
		this.subMerId = subMerId;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getBlackType() {
		return blackType;
	}
	public void setBlackType(String blackType) {
		this.blackType = blackType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getReserved1() {
		return reserved1;
	}
	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}
	public String getReserved2() {
		return reserved2;
	}
	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}
	public String getReserved3() {
		return reserved3;
	}
	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
