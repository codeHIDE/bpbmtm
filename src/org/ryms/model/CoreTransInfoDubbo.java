package org.ryms.model;

import java.io.Serializable;


/**
 * 核心交易传输类
 * 
 * @author Lynx
 * 
 */
public class CoreTransInfoDubbo implements Serializable
{
	
	private static final long serialVersionUID = -3143699344857365237L;
	
	private String transType;//交易类型
	private String transSource; //交易源
	private String messageType;// 消息类型
	private String pan;// 域2 卡号
	private String processingCode;// 域3 交易处理码
	private String amount;// 　域4  交易金额
	private String trackingNo;// 　域11  系统跟踪号 
	private String timeLocalTransaction;// 域12 受卡方时间
	private String dateLocalTransaction;// 域13 受卡方日期
	private String dateExpiration;// 域14  卡有效期
	private String settlement;// 域15 清算日期
	private String serviceEntryModeCode;//  域22 服务点输入方式码
	private String cardSeriNo;//  域23 卡片序列号
	private String serviceConditionCode;//  域25 服务点条件码
	private String servicePinCaptureCode;// 域26 服务点PIN 获取码
	private String identificationCode;// 域32 受理机构标识码
	private String track2Data;// 域35第二磁道数据
	private String track3Data;// 域36第三磁道数据
	private String referenceNo;// 域37 检索参考号
	private String authNo;//  域38 授权标识应答码
	private String responseCode;//　 域39 应答码
	private String responseDesc;// 错误描述
	private String terminalNo;//  域41 受卡机终端标识码
	private String merId;// 域42 受卡方标识码受卡方的标识码，即商户代码。
	private String merName;//  域43受卡方名称地址
	private String currencyCode;// 域49交易货币代码
	private String pinData;// 　 域52 个人标识码数据
	private String additionalAmounts;//  域54 实际余额
	private String dcData; //域55 IC卡信息域
	private String userDefined1;// 60域批次号
	private String userDefined2; // 域61 原始信息域
	private String userDefined3; //域63 自定义域
	private String mac;//  域64 报文鉴别码
	
	private String transKey; // 交易密钥（PIN）
	
	private String orderId; // 订单号
	private String userName;// 持卡人姓名
	private String idType;// 证件类型
	private String phone;// 手机号
	private String idNumber;//身份证号
	private String orgOrderId; // 原订单号
	
	private String clientIp;// 请求过来ip地址
	private String chMerId; //渠道商户号
	private String chTermId; //渠道终端号
	
	private String batchNo;
	
	
	
	
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	
	public String getTransSource() {
		return transSource;
	}
	public void setTransSource(String transSource) {
		this.transSource = transSource;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getProcessingCode() {
		return processingCode;
	}
	public void setProcessingCode(String processingCode) {
		this.processingCode = processingCode;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTrackingNo() {
		return trackingNo;
	}
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}
	public String getTimeLocalTransaction() {
		return timeLocalTransaction;
	}
	public void setTimeLocalTransaction(String timeLocalTransaction) {
		this.timeLocalTransaction = timeLocalTransaction;
	}
	public String getDateLocalTransaction() {
		return dateLocalTransaction;
	}
	public void setDateLocalTransaction(String dateLocalTransaction) {
		this.dateLocalTransaction = dateLocalTransaction;
	}
	public String getDateExpiration() {
		return dateExpiration;
	}
	public void setDateExpiration(String dateExpiration) {
		this.dateExpiration = dateExpiration;
	}
	public String getSettlement() {
		return settlement;
	}
	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}
	public String getServiceEntryModeCode() {
		return serviceEntryModeCode;
	}
	public void setServiceEntryModeCode(String serviceEntryModeCode) {
		this.serviceEntryModeCode = serviceEntryModeCode;
	}
	
	public String getCardSeriNo() {
		return cardSeriNo;
	}
	public void setCardSeriNo(String cardSeriNo) {
		this.cardSeriNo = cardSeriNo;
	}
	public String getServiceConditionCode() {
		return serviceConditionCode;
	}
	public void setServiceConditionCode(String serviceConditionCode) {
		this.serviceConditionCode = serviceConditionCode;
	}
	public String getServicePinCaptureCode() {
		return servicePinCaptureCode;
	}
	public void setServicePinCaptureCode(String servicePinCaptureCode) {
		this.servicePinCaptureCode = servicePinCaptureCode;
	}
	public String getIdentificationCode() {
		return identificationCode;
	}
	public void setIdentificationCode(String identificationCode) {
		this.identificationCode = identificationCode;
	}
	public String getTrack2Data() {
		return track2Data;
	}
	public void setTrack2Data(String track2Data) {
		this.track2Data = track2Data;
	}
	public String getTrack3Data() {
		return track3Data;
	}
	public void setTrack3Data(String track3Data) {
		this.track3Data = track3Data;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public String getAuthNo() {
		return authNo;
	}
	public void setAuthNo(String authNo) {
		this.authNo = authNo;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseDesc() {
		return responseDesc;
	}
	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}
	public String getTerminalNo() {
		return terminalNo;
	}
	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getPinData() {
		return pinData;
	}
	public void setPinData(String pinData) {
		this.pinData = pinData;
	}
	public String getAdditionalAmounts() {
		return additionalAmounts;
	}
	public void setAdditionalAmounts(String additionalAmounts) {
		this.additionalAmounts = additionalAmounts;
	}
	public String getDcData() {
		return dcData;
	}
	public void setDcData(String dcData) {
		this.dcData = dcData;
	}
	
	public String getUserDefined1() {
		return userDefined1;
	}
	public void setUserDefined1(String userDefined1) {
		this.userDefined1 = userDefined1;
	}
	public String getUserDefined2() {
		return userDefined2;
	}
	public void setUserDefined2(String userDefined2) {
		this.userDefined2 = userDefined2;
	}
	public String getUserDefined3() {
		return userDefined3;
	}
	public void setUserDefined3(String userDefined3) {
		this.userDefined3 = userDefined3;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getOrgOrderId() {
		return orgOrderId;
	}
	public void setOrgOrderId(String orgOrderId) {
		this.orgOrderId = orgOrderId;
	}
	public String getTransKey() {
		return transKey;
	}
	public void setTransKey(String transKey) {
		this.transKey = transKey;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getChMerId() {
		return chMerId;
	}
	public void setChMerId(String chMerId) {
		this.chMerId = chMerId;
	}
	public String getChTermId() {
		return chTermId;
	}
	public void setChTermId(String chTermId) {
		this.chTermId = chTermId;
	}

	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	@Override
	public String toString() {
		return "CoreTransInfo [additionalAmounts=" + additionalAmounts
				+ ", amount=" + amount + ", authNo=" + authNo + ", cardSeriNo="
				+ cardSeriNo + ", chMerId=" + chMerId + ", chTermId="
				+ chTermId + ", clientIp=" + clientIp + ", currencyCode="
				+ currencyCode + ", dateExpiration=" + dateExpiration
				+ ", dateLocalTransaction=" + dateLocalTransaction
				+ ", dcData=" + dcData + ", idNumber=" + idNumber + ", idType="
				+ idType + ", identificationCode=" + identificationCode
				+ ", mac=" + mac + ", merId=" + merId + ", merName=" + merName
				+ ", messageType=" + messageType + ", orderId=" + orderId
				+ ", orgOrderId=" + orgOrderId + ", pan=" + pan + ", phone="
				+ phone + ", pinData=" + pinData + ", processingCode="
				+ processingCode + ", referenceNo=" + referenceNo
				+ ", responseCode=" + responseCode + ", responseDesc="
				+ responseDesc + ", serviceConditionCode="
				+ serviceConditionCode + ", serviceEntryModeCode="
				+ serviceEntryModeCode + ", servicePinCaptureCode="
				+ servicePinCaptureCode + ", settlement=" + settlement
				+ ", terminalNo=" + terminalNo + ", timeLocalTransaction="
				+ timeLocalTransaction + ", track2Data=" + track2Data
				+ ", track3Data=" + track3Data + ", trackingNo=" + trackingNo
				+ ", transKey=" + transKey + ", transSource=" + transSource
				+ ", transType=" + transType + ", userDefined1=" + userDefined1
				+ ", userDefined2=" + userDefined2 + ", userDefined3="
				+ userDefined3 + ", userName=" + userName + "]";
	}
	
	
	
	
	
//	private String ext1;//扩展域
//	private String ext2;//扩展域
//	private String bindingNumber;// 用户绑定号
//	private String reservedDomain;// 保留域
//	private String cvn;// cvn
//	private String transKey;// 交易密钥
//	private String account2;
//	private String icNumber;
//	private String account2Name;
//	private String cardType;// 示随后记录为卡号或存折号(0:卡,1:折)代扣使用
//	private String bankId;// 开户行号如‘中国工商银行’代号：0102
//	private String spid;//区域码
//	private String ext;//扩展域
	
	
//	@Override
//	public String toString()
//	{
//		String obj = super.toString();
//		String res="{\"serialVersionUID\":"+serialVersionUID+",\"orderId\":"+orderId+",\"messageType\":"+messageType+",\"pan\":"+pan+",\"processingCode\":"+processingCode+",\"amount\":"+amount+",\"trackingNo\":"+trackingNo+",\"timeLocalTransaction\":"+timeLocalTransaction+",\"dateLocalTransaction\":"+dateLocalTransaction+",\"dateExpiration\":"+dateExpiration+",\"settlement\":"+settlement+",\"serviceEntryModeCode\":"+serviceEntryModeCode+",\"servicePinCaptureCode\":"+servicePinCaptureCode+",\"identificationCode\":"+identificationCode+",\"track3Data\":"+track3Data+",\"referenceNo\":"+referenceNo+",\"authNo\":"+authNo+",\"responseCode\":"+responseCode+",\"responseDesc\":"+responseDesc+",\"terminalNo\":"+terminalNo+",\"merId\":"+merId+",\"merName\":"+merName+",\"userName\":"+userName+",\"idType\":"+idType+",\"phone\":"+phone+",\"bindingNumber\":"+bindingNumber+",\"reservedDomain\":"+reservedDomain+",\"orgOrderId\":"+orgOrderId+",\"cvn\":"+cvn+",\"transKey\":"+transKey+",\"account2\":"+account2+",\"currencyCode\":"+currencyCode+",\"additionalAmounts\":"+additionalAmounts+",\"mac\":"+mac+",\"clientIp\":"+clientIp+",\"clientPort\":"+clientPort+",\"batchNo\":"+batchNo+",\"track2Data\":"+track2Data+",\"pinData\":"+pinData+",\"dcData\":"+dcData+",\"icNumber\":"+icNumber+",\"account2Name\":"+account2Name+",\"cardType\":"+cardType+",\"bankId\":"+bankId+",\"spid\":"+spid+",\"ext\":"+ext+",\"ext1\":"+ext1+",\"ext2\":"+ext2+",\"idNumber\":"+idNumber+"}";
//		String str=obj+res;
//		return str;
//	}

}
