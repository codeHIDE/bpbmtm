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
public class DrawMoneyRecord extends BeanParent{
	@XmlElement
	private String merchantId;	//	商户号	
	@XmlElement
	private String terminalId;	//	终端号
	@XmlElement
	private String merchantName;//商户名称
	@XmlElementWrapper(name="order")
	@XmlElement(name="orderInfo")
	private List<DrawMoneyRecordList> drawMoneyRecordLists = new ArrayList<DrawMoneyRecordList>();
	@XmlElement
	private String beginTime;	//	交易开始日期	beginTime	N(12)	C	R	
	@XmlElement
	private String endTime;	//	交易结束日期	endTime	N(12)	C	R	
	@XmlElement
	private String pageCount;	//	每页显示的数量
	@XmlElement
	private String curPage;	//	当前页数	
	@XmlElement
	private String totalCount;	//	总记录数	
	
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

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
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

	public List<DrawMoneyRecordList> getDrawMoneyRecordLists() {
		return drawMoneyRecordLists;
	}

	public void setDrawMoneyRecordLists(
			List<DrawMoneyRecordList> drawMoneyRecordLists) {
		this.drawMoneyRecordLists = drawMoneyRecordLists;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
}
