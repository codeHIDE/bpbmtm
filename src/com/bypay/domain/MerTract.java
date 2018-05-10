package com.bypay.domain;

public class MerTract {
	private String merSysId; 			// MER_SYS_ID BIGINT 1 否 外键
	private String tractId; 			// TRACT_ID INT 11 否 外键
	private String profitType; 			// PROFIT_TYPE VARCHAR 2 否 费率类型// 01：扣率 02：封顶  03：T+0扣率 04：T+0封顶
//	private String rate; 				// RATE VARCHAR 5 否 扣率值(单位：%)
//	private String highestFee; 			// HIGHEST_FEE VARCHAR 5 是 封顶值（单位：分）
//	private String lowestFee; 			// LOWEST_FEE VARCHAR 5 是 封低值（单位：分）
//	private String merRatio; 			// MER_RATIO VARCHAR 5 否 机构占比(单位：%)
	private String defaultStatus; 		// DEFALUT_STATUS VARCHAR 1 否 默认通道 0：非默认  1：默认通道
	private String status; 				// STATUS VARCHAR 1 否 状态 0：未使用 1：正在使用
	private String reserved; 			// RESERVED VARCHAR 50 是 扩展
	private MerInfo merInfo;
	private TractInfo tractInfo;
	
	public MerInfo getMerInfo() {
		return merInfo;
	}

	public void setMerInfo(MerInfo merInfo) {
		this.merInfo = merInfo;
	}

	public TractInfo getTractInfo() {
		return tractInfo;
	}

	public void setTractInfo(TractInfo tractInfo) {
		this.tractInfo = tractInfo;
	}

	public String getMerSysId() { 
		return merSysId;
	}

	public void setMerSysId(String merSysId) {
		this.merSysId = merSysId;
	}

	public String getTractId() {
		return tractId;
	}

	public void setTractId(String tractId) {
		this.tractId = tractId;
	}

	public String getProfitType() {
		return profitType;
	}

	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}


	public String getDefaultStatus() {
		return defaultStatus;
	}

	public void setDefaultStatus(String defaultStatus) {
		this.defaultStatus = defaultStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

}
