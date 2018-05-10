package com.bypay.domain.clientTool;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="bppos")
public class CreditInfoList extends BeanParent {

	@XmlElement
	private String mobileNum;//手机号
	@XmlElement
	private String legalManName;
	@XmlElement
	private String legalManIdcard;
	@XmlElement
	private String merchantId;    //商户号
	@XmlElement
	private String creditNumber;    //信用卡号
	@XmlElement
	private String transCode;    //接口类型
	@XmlElement
	private String picBuffer;    //图片
	
	public String getPicBuffer() {
		return picBuffer;
	}
	public void setPicBuffer(String picBuffer) {
		this.picBuffer = picBuffer;
	}
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	public String getLegalManName() {
		return legalManName;
	}
	public void setLegalManName(String legalManName) {
		this.legalManName = legalManName;
	}
	public String getLegalManIdcard() {
		return legalManIdcard;
	}
	public void setLegalManIdcard(String legalManIdcard) {
		this.legalManIdcard = legalManIdcard;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getCreditNumber() {
		return creditNumber;
	}
	public void setCreditNumber(String creditNumber) {
		this.creditNumber = creditNumber;
	}
	public String getTransCode() {
		return transCode;
	}
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	
	@Override
	public String toString() {
		return "CreditInfoList [mobileNum=" + mobileNum + ", legalManName="
				+ legalManName + ", legalManIdcard=" + legalManIdcard
				+ ", merchantId=" + merchantId + ", creditNumber="
				+ creditNumber + ", transCode=" + transCode + "]";
	}
	
	
}
