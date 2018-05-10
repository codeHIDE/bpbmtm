package com.bypay.domain;

public class WaresSpecInfo {
	private long specId;
	private String specName;
	private long waresId;
	private String waresPrice;
	private String waresShowPrice; 
	private int userType;
	private String createTime;
	public long getSpecId() {
		return specId;
	}
	public void setSpecId(long specId) {
		this.specId = specId;
	}
	public String getSpecName() {
		return specName;
	}
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	public long getWaresId() {
		return waresId;
	}
	public void setWaresId(long waresId) {
		this.waresId = waresId;
	}
	public String getWaresPrice() {
		return waresPrice;
	}
	public void setWaresPrice(String waresPrice) {
		this.waresPrice = waresPrice;
	}
	public String getWaresShowPrice() {
		return waresShowPrice;
	}
	public void setWaresShowPrice(String waresShowPrice) {
		this.waresShowPrice = waresShowPrice;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
