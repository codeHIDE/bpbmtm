package com.bypay.domain.clientTool;

import javax.xml.bind.annotation.XmlAccessType;//
import javax.xml.bind.annotation.XmlAccessorType;//
import javax.xml.bind.annotation.XmlElement;//
import javax.xml.bind.annotation.XmlRootElement;//

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="bppos")
public class OpenPurchase extends BeanParent{
	@XmlElement
	private String terminalId;//终端代码
	@XmlElement
	private String merchantId;//商户
	@XmlElement
	private String merchantName;//商户名
	@XmlElement
	private String settleAccountName;//结算账户名
	@XmlElement
	private String settleAccountNo;//结算账号
	@XmlElement
	private String bankNo;//开户行
	@XmlElement
	private String accountStatus;//开通状态
	@XmlElement
	private String terminalInFo;//终端信息
	@XmlElement
	private String factoryId;//厂商号
	
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
	public String getSettleAccountName() {
		return settleAccountName;
	}
	public void setSettleAccountName(String settleAccountName) {
		this.settleAccountName = settleAccountName;
	}
	public String getSettleAccountNo() {
		return settleAccountNo;
	}
	public void setSettleAccountNo(String settleAccountNo) {
		this.settleAccountNo = settleAccountNo;
	}
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getTerminalInFo() {
		return terminalInFo;
	}
	public void setTerminalInFo(String terminalInFo) {
		this.terminalInFo = terminalInFo;
	}
	
}
