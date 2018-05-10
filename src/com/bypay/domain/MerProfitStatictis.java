package com.bypay.domain;

public class MerProfitStatictis {

	private int id;
	private String mid;//商户号
	private String merType;//商户类型
	private String merSysId;//机构商号
	private String agentSysId;//上级代理商编号
	private String merName;//商户名称
	private String settleDate;//清算日期
	private String sumAmt;//总交易金额
	private String profit;//商户分润
	private String createTime;//创建时间
	private String clearStatus;//清分状态
	private String clearOrderId;//清分批次号
	private String faileAmt;//清分失败金额
	private String bpSerialNumber;//清分流水号
	private String countFee;//手续费
	
	
	//--------查询参数----------
	private String startTime;//开始时间
	private String endTime;//结束时间
	
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMerType() {
		return merType;
	}
	public void setMerType(String merType) {
		this.merType = merType;
	}
	public String getMerSysId() {
		return merSysId;
	}
	public void setMerSysId(String merSysId) {
		this.merSysId = merSysId;
	}
	public String getAgentSysId() {
		return agentSysId;
	}
	public void setAgentSysId(String agentSysId) {
		this.agentSysId = agentSysId;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public String getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
	public String getSumAmt() {
		return sumAmt;
	}
	public void setSumAmt(String sumAmt) {
		this.sumAmt = sumAmt;
	}
	public String getProfit() {
		return profit;
	}
	public void setProfit(String profit) {
		this.profit = profit;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getClearStatus() {
		return clearStatus;
	}
	public void setClearStatus(String clearStatus) {
		this.clearStatus = clearStatus;
	}
	public String getClearOrderId() {
		return clearOrderId;
	}
	public void setClearOrderId(String clearOrderId) {
		this.clearOrderId = clearOrderId;
	}
	public String getFaileAmt() {
		return faileAmt;
	}
	public void setFaileAmt(String faileAmt) {
		this.faileAmt = faileAmt;
	}
	public String getBpSerialNumber() {
		return bpSerialNumber;
	}
	public void setBpSerialNumber(String bpSerialNumber) {
		this.bpSerialNumber = bpSerialNumber;
	}
	public String getCountFee() {
		return countFee;
	}
	public void setCountFee(String countFee) {
		this.countFee = countFee;
	}
	
	
}
