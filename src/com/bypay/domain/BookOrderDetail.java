package com.bypay.domain;

public class BookOrderDetail {
	private long bookOrderDetailId;
	private long bookOrderId;
	private long waresId;
	private long specId;
	private String orderAmt;
	private int orderNum;
	public long getBookOrderDetailId() {
		return bookOrderDetailId;
	}
	public void setBookOrderDetailId(long bookOrderDetailId) {
		this.bookOrderDetailId = bookOrderDetailId;
	}
	public long getBookOrderId() {
		return bookOrderId;
	}
	public void setBookOrderId(long bookOrderId) {
		this.bookOrderId = bookOrderId;
	}
	public long getWaresId() {
		return waresId;
	}
	public void setWaresId(long waresId) {
		this.waresId = waresId;
	}
	public long getSpecId() {
		return specId;
	}
	public void setSpecId(long specId) {
		this.specId = specId;
	}
	public String getOrderAmt() {
		return orderAmt;
	}
	public void setOrderAmt(String orderAmt) {
		this.orderAmt = orderAmt;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	
	
	
}
