package com.bypay.domain.clientTool;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="bppos")
public class RegisterMerInfo extends BeanParent {
	//盛云接口
	@XmlElement
	private String custMobile;		//手机号码
	@XmlElement
	private String cardHandheld;	// 手持身份证照片
	@XmlElement
	private String cardFront;		// 身份证正面照片
	@XmlElement
	private String cardBack;		// 身份证背面照片
	@XmlElement
	private String custName;		//商户名称
	@XmlElement
	private String certificateType;	//证件类型
	@XmlElement
	private String certificateNo;	//证证件号码
	@XmlElement
	private String userEmail;		//邮箱
	@XmlElement
	private String provinceId;		//所在省ID
	@XmlElement
	private String cityId;			//所在市ID
	@XmlElement
	private String regionId;		//所在区ID
	@XmlElement
	private String identifyBackUrl;		//返回URL
	
	public String getIdentifyBackUrl() {
		return identifyBackUrl;
	}
	public void setIdentifyBackUrl(String identifyBackUrl) {
		this.identifyBackUrl = identifyBackUrl;
	}
	public String getCustMobile() {
		return custMobile;
	}
	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}
	public String getCardHandheld() {
		return cardHandheld;
	}
	public void setCardHandheld(String cardHandheld) {
		this.cardHandheld = cardHandheld;
	}
	public String getCardFront() {
		return cardFront;
	}
	public void setCardFront(String cardFront) {
		this.cardFront = cardFront;
	}
	public String getCardBack() {
		return cardBack;
	}
	public void setCardBack(String cardBack) {
		this.cardBack = cardBack;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}
	public String getCertificateNo() {
		return certificateNo;
	}
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
		
	
}
