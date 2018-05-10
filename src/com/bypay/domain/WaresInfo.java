package com.bypay.domain;

public class WaresInfo {
	private long waresId;
	private String waresName;
	private String merchantId;
	private String waresPrice;
	private String waresShowPrice;
	private int waresType;
	private int waresStock;
	private String createTime;
	public long getWaresId() {
		return waresId;
	}
	public void setWaresId(long waresId) {
		this.waresId = waresId;
	}
	public String getWaresName() {
		return waresName;
	}
	public void setWaresName(String waresName) {
		this.waresName = waresName;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
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
	public int getWaresType() {
		return waresType;
	}
	public void setWaresType(int waresType) {
		this.waresType = waresType;
	}
	public int getWaresStock() {
		return waresStock;
	}
	public void setWaresStock(int waresStock) {
		this.waresStock = waresStock;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
