package com.bypay.domain.clientTool;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="bppos")
public class AccountLogIn extends BeanParent {
	@XmlElement
	private String terminalId;	//	终端代码	terminalId	N(16)	M	M	
	@XmlElement
	private String merchantId;	//	登录商户号	merchantId	N30	M		
	@XmlElement
	private String merchantName;	//	商户名	merchantName	ans…100		M	
	@XmlElement
	private String accountName;	//	账户名	accountName	ans…20	M	R	
	@XmlElement
	private String accountPwd;	//	密码	accountPwd	ans…30	M	R	
	@XmlElement
	private String personalMerRegNo;	//	营业执照	personalMerRegNo	ans…30		M
	@XmlElement
	private String taxNo;	//	税务登记号	taxNo	an…20	C	R	
	@XmlElement
	private String occNo;	//	组织代码号	occNo	Ans…20	C		
	@XmlElement
	private String legalManIdcard;	//	法人身份证号	legalManIdcard	an18		M	
	@XmlElement
	private String settleAccount;	//	结算账户名	settleAccount	ans….50		M	
	@XmlElement
	private String settleAccountNo;	//	结算账号	setttleAccountNo			M	
	@XmlElement
	private String settleAgency;	//	开户行	settleAgency	ans…50		M	
	@XmlElement
	private String accountStatus;	//	开户状态	accountStatus	n2		M	0：审核中	1：审核成功	2：审核失败
	@XmlElement
	private String authStatus;	//	认证状态	authStatus	N2		M	0认证中	1实名认证成功	2实人认证成功	3成功开通收款	4实人认证失败
	@XmlElement
	private String settleAccountType;	//	结算账户类型	settleAccountType	n2		M	1对公2对私
	@XmlElement
	private String terminalInFo;	//	终端信息	terminalInFo	ANS(256)	C		手机串号、号码、os、序列号等	psn=xxx|pn=xxxx|os=xxx|sn=xxxx
	@XmlElement
	private String factoryId;// 厂商号
	@XmlElement
	private String transRate;// 子商户交易费率（扣率）
	@XmlElement
	private String userStatus;// 用户状态
	@XmlElement
	private String reMack;// 审核备注
	@XmlElement
	private String msgExt;//附加信息
	
	public String getMsgExt() {
		return msgExt;
	}
	public void setMsgExt(String msgExt) {
		this.msgExt = msgExt;
	}
	public String getReMack() {
		return reMack;
	}
	public void setReMack(String reMack) {
		this.reMack = reMack;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getFactoryId() {
		return factoryId;
	}
	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountPwd() {
		return accountPwd;
	}
	public void setAccountPwd(String accountPwd) {
		this.accountPwd = accountPwd;
	}
	public String getPersonalMerRegNo() {
		return personalMerRegNo;
	}
	public void setPersonalMerRegNo(String personalMerRegNo) {
		this.personalMerRegNo = personalMerRegNo;
	}
	public String getTaxNo() {
		return taxNo;
	}
	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}
	public String getOccNo() {
		return occNo;
	}
	public void setOccNo(String occNo) {
		this.occNo = occNo;
	}
	public String getLegalManIdcard() {
		return legalManIdcard;
	}
	public void setLegalManIdcard(String legalManIdcard) {
		this.legalManIdcard = legalManIdcard;
	}
	public String getSettleAccount() {
		return settleAccount;
	}
	public void setSettleAccount(String settleAccount) {
		this.settleAccount = settleAccount;
	}
	public String getSettleAccountNo() {
		return settleAccountNo;
	}
	public void setSettleAccountNo(String settleAccountNo) {
		this.settleAccountNo = settleAccountNo;
	}
	public String getSettleAgency() {
		return settleAgency;
	}
	public void setSettleAgency(String settleAgency) {
		this.settleAgency = settleAgency;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}
	public String getSettleAccountType() {
		return settleAccountType;
	}
	public void setSettleAccountType(String settleAccountType) {
		this.settleAccountType = settleAccountType;
	}
	public String getTerminalInFo() {
		return terminalInFo;
	}
	public void setTerminalInFo(String terminalInFo) {
		this.terminalInFo = terminalInFo;
	}
	public String getTransRate() {
		return transRate;
	}
	public void setTransRate(String transRate) {
		this.transRate = transRate;
	}
	
}
