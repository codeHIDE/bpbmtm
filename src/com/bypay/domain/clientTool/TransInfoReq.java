package com.bypay.domain.clientTool;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement(name="upbp")
@XmlType(propOrder={"application","version","sendTime","transCode","merchantName","merchantId","merchantOrderId","merchantOrderTime",
		"merchantOrderAmt","merchantOrderCurrency","merchantOrderDesc","transTimeout","gwType","bizType","transType","backUrl",
		"bpSerialNum","transNum","countFee","lists","balance","chargeBalance","msgExt","misc","respCode","respDesc","sign"})
public class TransInfoReq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1734736615295238129L;

	/*
	 * 单笔代发 前置平台-业务平台交互 批量代发 合并 将单笔代发做为批量代发的一种情况
	 */

	/**
	 * 应用名称 支付前置：GwBiz.Req 业务平台：BizGw.Rsp
	 */
	private String application;
	/**
	 * 通讯协议版本号 1.0.0(现默认填该值)
	 */
	private String version;
	/**
	 * 发送时间 发送报文的时间，格式为：YYYYMMDDhhmmss
	 */
	private String sendTime;
	/**
	 * 交易代码 7010
	 */
	private String transCode;
	/**
	 * 商户名称
	 */
	private String merchantName;
	/**
	 * 商户编号
	 */
	private String merchantId;
	/**
	 * 商户订单号
	 */
	private String merchantOrderId;
	/**
	 * 商户订单时间(yyyyMMddHHmmss)
	 */
	private String merchantOrderTime;
	/**
	 * 商户订单金额
	 */
	private String merchantOrderAmt;
	/**
	 * 订单金额币种 默认为156
	 */
	private String merchantOrderCurrency;
	/**
	 * 商户订单描述
	 */
	private String merchantOrderDesc;
	/**
	 * 交易超时时间(yyyyMMddHHmmss)
	 */
	private String transTimeout;
	/**
	 * 支付网关类型(00)
	 */
	private String gwType;
	/**
	 * 业务类型06代发
	 */
	private String bizType;
	/**
	 * 交易类型10代发
	 */
	private String transType;
	/**
	 * 后台通知URL 用来接收交易结果通知的后台URL
	 */
	private String backUrl;

	/**
	 * 自定义保留域
	 */
	private String misc;
	/**
	 * 网关流水号 业务平台流水号，通过预下单的交易必填，此字段作为退款撤销依据
	 */
	private String bpSerialNum;
	/**
	 * 应答码 0000：成功；其他为失败，详细见附录应答码汇总表其他为失败，详细见附录应答码汇总表
	 */
	private String respCode;
	/**
	 * 应答码描述返回详细的操作结果信息
	 */
	private String respDesc;

	/**
	 * 交易笔数
	 */
	private String transNum;
	/**
	 * 交易成功的手续费总额
	 */
	private String countFee;
	/**
	 * 批量信息
	 */
	private List<ProxyPay> lists;

	/*
	 * 代发查询
	 */

	/**
	 * 附加信息
	 */
	private String msgExt;
	/**
	 * md5校验
	 */
	private String sign;

	/*
	 * 代发商户余额查询
	 */


	private String balance;
	private String chargeBalance;

	@XmlAttribute(name="application")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	@XmlAttribute(name="version")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@XmlAttribute(name="sendTime")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	@XmlElement(name="transCode")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	@XmlElement(name="merchantName")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	@XmlElement(name="merchantId")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	@XmlElement(name="merchantOrderId")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(String merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}
	

	@XmlElement(name="merchantOrderTime")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getMerchantOrderTime() {
		return merchantOrderTime;
	}

	public void setMerchantOrderTime(String merchantOrderTime) {
		this.merchantOrderTime = merchantOrderTime;
	}

	@XmlElement(name="merchantOrderAmt")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getMerchantOrderAmt() {
		return merchantOrderAmt;
	}

	public void setMerchantOrderAmt(String merchantOrderAmt) {
		this.merchantOrderAmt = merchantOrderAmt;
	}

	@XmlElement(name="merchantOrderCurrency")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getMerchantOrderCurrency() {
		return merchantOrderCurrency;
	}

	public void setMerchantOrderCurrency(String merchantOrderCurrency) {
		this.merchantOrderCurrency = merchantOrderCurrency;
	}

	@XmlElement(name="merchantOrderDesc")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getMerchantOrderDesc() {
		return merchantOrderDesc;
	}

	public void setMerchantOrderDesc(String merchantOrderDesc) {
		this.merchantOrderDesc = merchantOrderDesc;
	}

	@XmlElement(name="transTimeout")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getTransTimeout() {
		return transTimeout;
	}

	public void setTransTimeout(String transTimeout) {
		this.transTimeout = transTimeout;
	}

	@XmlElement(name="gwType")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getGwType() {
		return gwType;
	}

	public void setGwType(String gwType) {
		this.gwType = gwType;
	}

	@XmlElement(name="bizType")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	@XmlElement(name="transType")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getTransType() {
		return transType;
	}


	public void setTransType(String transType) {
		this.transType = transType;
	}

	@XmlElement(name="backUrl")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	@XmlElement(name="misc")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getMisc() {
		return misc;
	}

	public void setMisc(String misc) {
		this.misc = misc;
	}

	@XmlElement(name="bpSerialNum")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getBpSerialNum() {
		return bpSerialNum;
	}

	public void setBpSerialNum(String bpSerialNum) {
		this.bpSerialNum = bpSerialNum;
	}

	@XmlElement(name="respCode")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	@XmlElement(name="respDesc")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getRespDesc() {
		return respDesc;
	}

	
	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}

	@XmlElement(name="transNum")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getTransNum() {
		return transNum;
	}

	public void setTransNum(String transNum) {
		this.transNum = transNum;
	}

	public void setLists(List<ProxyPay> lists) {
		this.lists = lists;
	}

	@XmlElement(name="list")
	@XmlElementWrapper(name="lists")
	public List<ProxyPay> getLists() {
		return lists;
	}

	@XmlElement(name="msgExt")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getMsgExt() {
		return msgExt;
	}

	public void setMsgExt(String msgExt) {
		this.msgExt = msgExt;
	}

	@XmlElement(name="balance")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	@XmlElement(name="chargeBalance")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getChargeBalance() {
		return chargeBalance;
	}

	public void setChargeBalance(String chargeBalance) {
		this.chargeBalance = chargeBalance;
	}

	@XmlElement(name="countFee")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getCountFee() {
		return countFee;
	}

	public void setCountFee(String countFee) {
		this.countFee = countFee;
	}

	@XmlElement(name="sign")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
