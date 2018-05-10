package com.bypay.domain.clientTool;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="bppos")
public class Refund extends BeanParent{
	
	@XmlElement
	private String merchantId;//商户代码
	@XmlElement
	private String terminalId;//终端代码M
	@XmlElement
	private String platformId;//平台代码M
	@XmlElement
	private String transType;//交易类型码M
	@XmlElement
	private String transAmt;//交易金额M
	@XmlElement
	private String merchantOrderId;//商户订单号M
	@XmlElement
	private String orgTransId;//原交易流水号M
	@XmlElement
	private String orgTransTime;//原交易日期M
	@XmlElement
	private String orgMerchantOrderId;//原商户订单号M
	@XmlElement
	private String transId;//交易流水号
	@XmlElement
	private String transTime;//交易日期
	@XmlElement
	private String accountNumber;//主账户
	@XmlElement
	private String merchantSign;//商户数字签名C
	@XmlElement
	private String senderSign;//发送方数字签名C
	@XmlElement
	private String serviceInfo;//服务信息C
	@XmlElement
	private String terminalInfo;//终端信息M
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(String transAmt) {
		this.transAmt = transAmt;
	}
	public String getMerchantOrderId() {
		return merchantOrderId;
	}
	public void setMerchantOrderId(String merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}
	public String getOrgTransId() {
		return orgTransId;
	}
	public void setOrgTransId(String orgTransId) {
		this.orgTransId = orgTransId;
	}
	public String getOrgTransTime() {
		return orgTransTime;
	}
	public void setOrgTransTime(String orgTransTime) {
		this.orgTransTime = orgTransTime;
	}
	public String getOrgMerchantOrderId() {
		return orgMerchantOrderId;
	}
	public void setOrgMerchantOrderId(String orgMerchantOrderId) {
		this.orgMerchantOrderId = orgMerchantOrderId;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getTransTime() {
		return transTime;
	}
	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}
	public String getMerchantSign() {
		return merchantSign;
	}
	public void setMerchantSign(String merchantSign) {
		this.merchantSign = merchantSign;
	}
	public String getSenderSign() {
		return senderSign;
	}
	public void setSenderSign(String senderSign) {
		this.senderSign = senderSign;
	}
	public String getServiceInfo() {
		return serviceInfo;
	}
	public void setServiceInfo(String serviceInfo) {
		this.serviceInfo = serviceInfo;
	}
	public String getTerminalInfo() {
		return terminalInfo;
	}
	public void setTerminalInfo(String terminalInfo) {
		this.terminalInfo = terminalInfo;
	}
	
}
