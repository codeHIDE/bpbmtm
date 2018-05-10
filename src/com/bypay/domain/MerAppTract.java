package com.bypay.domain;

public class MerAppTract {

	private String id;
	private String merSysId;	//	MER_SYS_ID	BIGINT		1	否	机构商号
	private String appTractId;	//	APP_TRACT_ID	INT	11	否	应用通道号
	private String transMerId;	//	TRANS_MER_ID	VARCHAR	20	否	交易商户号
	private String transRate;	//	TRANS_RATE	VARCHAR	10	是	交易扣率
	private String lowestFee;	//	LOWEST_FEE	VARCHAR	10	是	最低手续费
	private String highestFee;	//	HIGHEST_FEE	VARCHAR	10	是	最高手续费
	private String merProfit;	//	MER_PROFIT	VARCHAR	10	是	机构分润比例
	private String status;		//	STATUS	VARCHAR	1	是	状态 	0未使用   1正在使用
	private String createTime;	//	CREATE_TIME	VARCHAR	20	是	创建时间
	private String appTractType;//	APP_TRACT_TYPE	VARCHAR	2	是	通道类别
					//	01:还款	02:转账	03:余额查询
	private String remark;		//	REMARK	VARCHAR	100	是	备注
	private String reserved;	//	RESERVED	VARCHAR	50	是	扩展
	private AppTractInfo appTractInfo;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public AppTractInfo getAppTractInfo() {
		return appTractInfo;
	}
	public String getAppTractType() {
		return appTractType;
	}
	public void setAppTractType(String appTractType) {
		this.appTractType = appTractType;
	}
	public void setAppTractInfo(AppTractInfo appTractInfo) {
		this.appTractInfo = appTractInfo;
	}

	public String getMerSysId() {
		return merSysId;
	}

	public void setMerSysId(String merSysId) {
		this.merSysId = merSysId;
	}

	public String getAppTractId() {
		return appTractId;
	}

	public void setAppTractId(String appTractId) {
		this.appTractId = appTractId;
	}

	public String getTransMerId() {
		return transMerId;
	}

	public void setTransMerId(String transMerId) {
		this.transMerId = transMerId;
	}

	public String getTransRate() {
		return transRate;
	}

	public void setTransRate(String transRate) {
		this.transRate = transRate;
	}

	public String getLowestFee() {
		return lowestFee;
	}

	public void setLowestFee(String lowestFee) {
		this.lowestFee = lowestFee;
	}

	public String getHighestFee() {
		return highestFee;
	}

	public void setHighestFee(String highestFee) {
		this.highestFee = highestFee;
	}

	public String getMerProfit() {
		return merProfit;
	}

	public void setMerProfit(String merProfit) {
		this.merProfit = merProfit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
}
