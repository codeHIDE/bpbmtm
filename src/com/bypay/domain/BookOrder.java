package com.bypay.domain;

public class BookOrder {
	private long bookOrderId;
	private long webOrderId;
	private long userId;
	private String bookDate;
	private String bookTime;
	private String finishTime;
	private int detailNum;
	private String totalAmt;
	private int orderStatus;
	public long getBookOrderId() {
		return bookOrderId;
	}
	public void setBookOrderId(long bookOrderId) {
		this.bookOrderId = bookOrderId;
	}
	public long getWebOrderId() {
		return webOrderId;
	}
	public void setWebOrderId(long webOrderId) {
		this.webOrderId = webOrderId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getBookDate() {
		return bookDate;
	}
	public void setBookDate(String bookDate) {
		this.bookDate = bookDate;
	}
	public String getBookTime() {
		return bookTime;
	}
	public void setBookTime(String bookTime) {
		this.bookTime = bookTime;
	}
	public String getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	public int getDetailNum() {
		return detailNum;
	}
	public void setDetailNum(int detailNum) {
		this.detailNum = detailNum;
	}
	public String getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
}
