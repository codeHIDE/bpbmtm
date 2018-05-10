package com.bypay.domain.clientTool;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="bppos")
public class SelectPassWord  extends BeanParent{
	
	@XmlElement
	private String merchantId;   //商户号
	@XmlElement
	private String phoneNum; //册手机号
	@XmlElement
	private String settleAccountName; //结算账户名
	@XmlElement
	private String setttleAccountNo;	//结算账户号
	@XmlElement
	private String pwd;   //新密码
	@XmlElement
	private String terminalInFo;  //终端信息
	@XmlElement
	private String terminalId;  //终端代码
	@XmlElement
	private String factoryId; //厂商号
	
	public String getFactoryId() {
		return factoryId;
	}
	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getSettleAccountName() {
		return settleAccountName;
	}
	public void setSettleAccountName(String settleAccountName) {
		this.settleAccountName = settleAccountName;
	}
	public String getSetttleAccountNo() {
		return setttleAccountNo;
	}
	public void setSetttleAccountNo(String setttleAccountNo) {
		this.setttleAccountNo = setttleAccountNo;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getTerminalInFo() {
		return terminalInFo;
	}
	public void setTerminalInFo(String terminalInFo) {
		this.terminalInFo = terminalInFo;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	
}
