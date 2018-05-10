package com.bypay.domain.clientTool;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="bppos")
public class Enquiry extends BeanParent{
	@XmlElementWrapper(name="List")
	@XmlElement(name="orderInfo")
	private List<EnquiryList> enquiryList = new ArrayList<EnquiryList>();
	@XmlElement
	private String merchantId;	//	商户代码	merchantId	N(15)	C	M	
	@XmlElement
	private String merchantName;	//	商户名称	merchantName	ans..64		M	
	@XmlElement
	private String terminalId;	//	终端代码	terminalId	N(13)	M	R	
	@XmlElement
	private String platFormId;	//	平台代码	platformId	N(15)	M		
	@XmlElement
	private String transType;	//	交易类型码	transType	N(6)	C	R	100000：消费200000：撤销    100001 ：余额查询  100002：信用卡还款100003：转账100004：手机充值
	@XmlElement
	private String transId;	//	交易流水号	transId	N(16)	C	M	
	@XmlElement
	private String beginTime;	//	交易开始日期	beginTime	N(12)	C	R	
	@XmlElement
	private String endTime;	//	交易结束日期	endTime	N(12)	C	R	
	@XmlElement
	private String pageCount;	//	每页显示的数量	pageCount	N(2)	C	R	不填默认为10
	@XmlElement
	private String curPage;	//	当前页数	curPage	N(5)	C	R	不填默认为1
	@XmlElement
	private String totalCount;	//	总记录数	totalCount	N(5)		M	
	@XmlElement
	private String factoryId; //厂商号
	
	public String getFactoryId() {
		return factoryId;
	}
	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}
	public List<EnquiryList> getEnquiryList() {
		return enquiryList;
	}
	public void setEnquiryList(List<EnquiryList> enquiryList) {
		this.enquiryList = enquiryList;
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
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getPlatFormId() {
		return platFormId;
	}
	public void setPlatFormId(String platFormId) {
		this.platFormId = platFormId;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getPageCount() {
		return pageCount;
	}
	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}
	public String getCurPage() {
		return curPage;
	}
	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	
}

