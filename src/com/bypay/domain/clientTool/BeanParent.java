package com.bypay.domain.clientTool;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.bypay.util.DateUtil;


@XmlAccessorType(XmlAccessType.NONE)
public class BeanParent {

	@XmlAttribute
	private String application;//应用名称
	@XmlAttribute
	private String version;//终端版本号
	@XmlAttribute
	private String sendTime;//发送时间
	@XmlAttribute
	private String sendSeqId;//发送流水号
	@XmlElement
	private String msgExt;//附加信息
	@XmlElement
	private String misc;//自定义保留域
	@XmlElement
	private String respCode;//应答码
	@XmlElement
	private String respDesc;//应答码描述
	@XmlElement
	private String txnDate;		//交易日期
	@XmlElement
	private String txnTime;		//交易时间
	@XmlElement
	private String agentId;		//合作编号
	
	
	public String getTxnDate() {
		return txnDate;
	}
	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}
	public String getTxnTime() {
		return txnTime;
	}
	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getSendSeqId() {
		return sendSeqId;
	}
	public void setSendSeqId(String sendSeqId) {
		this.sendSeqId = sendSeqId;
	}
	public String getMsgExt() {
		return msgExt;
	}
	public void setMsgExt(String msgExt) {
		this.msgExt = msgExt;
	}
	public String getMisc() {
		return misc;
	}
	public void setMisc(String misc) {
		this.misc = misc;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespDesc() {
		return respDesc;
	}
	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	} 
	
	public void copyBusBeanParent(BeanParent beanParent){
		this.application = beanParent.getApplication().substring(0,
				beanParent.getApplication().indexOf(".")+1)+"Rsp";
		this.version = "1.0.0";
		this.sendTime = DateUtil.getDate("yyyyMMddHHmmss");
		this.sendSeqId = beanParent.getSendSeqId();
	}
}
