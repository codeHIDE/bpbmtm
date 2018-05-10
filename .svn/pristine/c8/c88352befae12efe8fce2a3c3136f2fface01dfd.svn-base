package com.bypay.domain.clientTool;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="bppos")
public class PrdOrderInfo extends BeanParent {
	//盛云接口
	@XmlElement
	private String custId;		//商户编号
	@XmlElement
	private String custMobile;		//手机号码
	@XmlElement
	private String prdordType;	// 商品类型
	@XmlElement
	private String prdordAmt;		// 订单金额
	@XmlElement
	private String agentOrderNo;		// 订单编号
	@XmlElement
	private String PayBackUrl;		//订单成功回调地址
	
	public String getCustMobile() {
		return custMobile;
	}
	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getPrdordType() {
		return prdordType;
	}
	public void setPrdordType(String prdordType) {
		this.prdordType = prdordType;
	}
	public String getPrdordAmt() {
		return prdordAmt;
	}
	public void setPrdordAmt(String prdordAmt) {
		this.prdordAmt = prdordAmt;
	}
	public String getAgentOrderNo() {
		return agentOrderNo;
	}
	public void setAgentOrderNo(String agentOrderNo) {
		this.agentOrderNo = agentOrderNo;
	}
	public String getPayBackUrl() {
		return PayBackUrl;
	}
	public void setPayBackUrl(String payBackUrl) {
		PayBackUrl = payBackUrl;
	}
	
	
}
