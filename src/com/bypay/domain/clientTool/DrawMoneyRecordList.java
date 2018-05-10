package com.bypay.domain.clientTool;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="order")
public class DrawMoneyRecordList {
	@XmlElement
	private String orderAmt;//提现金额
	@XmlElement
	private String currency;//账户余额币种
	@XmlElement
	private String merchantOrderId;//商户订单号
	@XmlElement
	private String merchantOrderTime;//商户订单时间
	@XmlElement
	private String orderStatus;//提款状态
	public String getOrderAmt() {
		return orderAmt;
	}
	public void setOrderAmt(String orderAmt) {
		this.orderAmt = orderAmt;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getMerchantOrderId() {
		return merchantOrderId;
	}
	public void setMerchantOrderId(String merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}
	public String getMerchantOrderTime() {
		return merchantOrderTime;
	}
	public void setMerchantOrderTime(String merchantOrderTime) {
		this.merchantOrderTime = merchantOrderTime;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
//	@XmlElement
//	private String subMerId;//商户号
//	@XmlElement
//	private String merSysId;//机构商户号
//	@XmlElement
//	private String orderStatus;//提现状态
//	@XmlElement
//	private String createTime;//申请时间
//	@XmlElement
//	private String finishTime;//完成时间
//	@XmlElement
//	private String transId;//提现订单号
//	@XmlElement
//	private String transQId;//提现流水号
//	@XmlElement
//	private String transFee;//手续费
//	@XmlElement
//	private String batchId;	//	批次号
	
	
}
