package com.bypay.domain.clientTool;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="bppos")
public class TerminalValidate extends BeanParent{
	@XmlElement
	private String terminalId; // 终端号
	@XmlElement
	private String versionCode; // 当前版本号
	@XmlElement
	private String terminalSysterm; //终端系统
	@XmlElement
	private String terminalStatus;  //终端状态
	@XmlElement
	private String versionDesc; //版本信息
	@XmlElement
	private String updateType; //更新状态
	@XmlElement
	private String merchantId; //商户号
	@XmlElement
	private String updatePath; //更新地址
	@XmlElement
	private String merSysId; //更新地址
	@XmlElement
	private String factoryId; // 厂商号
	@XmlElement
	private String t0Status; // T+0状态 0 未开通 1 已开通
	@XmlElement
	private String d1MerRate;//D+1费率
	@XmlElement
	private String t0MerRate;//T+0费率
	@XmlElement
	private String lowsetMentionAmt;//起结金额
	@XmlElement
	private String agreementStatus;//电子协议阅读状态
	
	public String getAgreementStatus() {
		return agreementStatus;
	}
	public void setAgreementStatus(String agreementStatus) {
		this.agreementStatus = agreementStatus;
	}
	public String getT0Status() {
		return t0Status;
	}
	public void setT0Status(String t0Status) {
		this.t0Status = t0Status;
	}
	public String getFactoryId() {
		return factoryId;
	}
	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}
	public String getMerSysId() {
		return merSysId;
	}
	public void setMerSysId(String merSysId) {
		this.merSysId = merSysId;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	public String getTerminalSysterm() {
		return terminalSysterm;
	}
	public void setTerminalSysterm(String terminalSysterm) {
		this.terminalSysterm = terminalSysterm;
	}
	public String getTerminalStatus() {
		return terminalStatus;
	}
	public void setTerminalStatus(String terminalStatus) {
		this.terminalStatus = terminalStatus;
	}
	public String getVersionDesc() {
		return versionDesc;
	}
	public void setVersionDesc(String versionDesc) {
		this.versionDesc = versionDesc;
	}
	public String getUpdateType() {
		return updateType;
	}
	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getUpdatePath() {
		return updatePath;
	}
	public void setUpdatePath(String updatePath) {
		this.updatePath = updatePath;
	}
	public String getD1MerRate() {
		return d1MerRate;
	}
	public void setD1MerRate(String d1MerRate) {
		this.d1MerRate = d1MerRate;
	}
	public String getT0MerRate() {
		return t0MerRate;
	}
	public void setT0MerRate(String t0MerRate) {
		this.t0MerRate = t0MerRate;
	}
	public String getLowsetMentionAmt() {
		return lowsetMentionAmt;
	}
	public void setLowsetMentionAmt(String lowsetMentionAmt) {
		this.lowsetMentionAmt = lowsetMentionAmt;
	}
	
}
