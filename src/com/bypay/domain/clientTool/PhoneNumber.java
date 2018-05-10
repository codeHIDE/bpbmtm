package com.bypay.domain.clientTool;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="bppos")
public class PhoneNumber extends BeanParent{
	
	@XmlElement
	private String terminalId;	//	终端号
	@XmlElement
	private String phoneNum;//商户名称
	@XmlElement
	private String message;//短信内容
	@XmlElement
	private String msgExt1;
	@XmlElement
	private String msgExt2;
	@XmlElement
	private String msgExt3;
	
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMsgExt1() {
		return msgExt1;
	}
	public void setMsgExt1(String msgExt1) {
		this.msgExt1 = msgExt1;
	}
	public String getMsgExt2() {
		return msgExt2;
	}
	public void setMsgExt2(String msgExt2) {
		this.msgExt2 = msgExt2;
	}
	public String getMsgExt3() {
		return msgExt3;
	}
	public void setMsgExt3(String msgExt3) {
		this.msgExt3 = msgExt3;
	}

}
