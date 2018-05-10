package com.bypay.domain;

public class MccCode {
	
	private String id;					//	ID	BIGINT	8	否	自增长
	private String mcc;				//MCC	VARCHAR	10	否	代码
	private String desc;				//DESC	VARCHAR	50	否	描述
	private String profit;				//PROFIT	VARCHAR	50	是	基准费率
	private String remark;			//REMARK	VARCHAR	50	是	备注
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMcc() {
		return mcc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getProfit() {
		return profit;
	}
	public void setProfit(String profit) {
		this.profit = profit;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
