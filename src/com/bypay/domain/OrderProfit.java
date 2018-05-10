package com.bypay.domain;

public class OrderProfit {

	private long id;//自增长
	private String orderId;//订单号
	private String merSysId;//机构商户号
	private String merAmt;
	private String merProfit;//机构利润
	private String merRate;//机构费率
	private String agentL1Id;//1级代理商户号
	private String agent1Rate;
	private String agent1HightFee;
	private String agent1Profit;//一级代理利润
	private String agentL2Id;//2级代理商户号
	private String agent2Profit;//二级代理利润
	private String agent2Rate;
	private String agent2HightFee;
	private String platId;//平台商户号
	private String platProfit;//平台利润
	private String bypayProfit;//BYPAY利润
	private String tractFee;//通道费用
	private String merHightFee;
	private String tractBypayProfit;//通道中的平台利润
	private String tractAcqbankProfit;//通道中收单行利润
	private String tractCost;//通道成本
	private String reserved;//扩展
	private String createTime;
	private String createDate;
	private String subMerRate;
	private String subMerHightFee;
	
	
	public String getMerAmt() {
		return merAmt;
	}
	public void setMerAmt(String merAmt) {
		this.merAmt = merAmt;
	}
	public String getAgent1Rate() {
		return agent1Rate;
	}
	public void setAgent1Rate(String agent1Rate) {
		this.agent1Rate = agent1Rate;
	}
	public String getAgent1HightFee() {
		return agent1HightFee;
	}
	public void setAgent1HightFee(String agent1HightFee) {
		this.agent1HightFee = agent1HightFee;
	}
	public String getAgent2Rate() {
		return agent2Rate;
	}
	public void setAgent2Rate(String agent2Rate) {
		this.agent2Rate = agent2Rate;
	}
	public String getAgent2HightFee() {
		return agent2HightFee;
	}
	public void setAgent2HightFee(String agent2HightFee) {
		this.agent2HightFee = agent2HightFee;
	}
	public String getMerHightFee() {
		return merHightFee;
	}
	public void setMerHightFee(String merHightFee) {
		this.merHightFee = merHightFee;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getSubMerRate() {
		return subMerRate;
	}
	public void setSubMerRate(String subMerRate) {
		this.subMerRate = subMerRate;
	}
	public String getSubMerHightFee() {
		return subMerHightFee;
	}
	public void setSubMerHightFee(String subMerHightFee) {
		this.subMerHightFee = subMerHightFee;
	}
	public String getMerRate() {
		return merRate;
	}
	public void setMerRate(String merRate) {
		this.merRate = merRate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getMerSysId() {
		return merSysId;
	}
	public void setMerSysId(String merSysId) {
		this.merSysId = merSysId;
	}
	public String getMerProfit() {
		return merProfit;
	}
	public void setMerProfit(String merProfit) {
		this.merProfit = merProfit;
	}
	public String getAgentL1Id() {
		return agentL1Id;
	}
	public void setAgentL1Id(String agentL1Id) {
		this.agentL1Id = agentL1Id;
	}
	public String getAgent1Profit() {
		return agent1Profit;
	}
	public void setAgent1Profit(String agent1Profit) {
		this.agent1Profit = agent1Profit;
	}
	public String getAgentL2Id() {
		return agentL2Id;
	}
	public void setAgentL2Id(String agentL2Id) {
		this.agentL2Id = agentL2Id;
	}
	public String getAgent2Profit() {
		return agent2Profit;
	}
	public void setAgent2Profit(String agent2Profit) {
		this.agent2Profit = agent2Profit;
	}
	public String getPlatId() {
		return platId;
	}
	public void setPlatId(String platId) {
		this.platId = platId;
	}
	public String getPlatProfit() {
		return platProfit;
	}
	public void setPlatProfit(String platProfit) {
		this.platProfit = platProfit;
	}
	public String getBypayProfit() {
		return bypayProfit;
	}
	public void setBypayProfit(String bypayProfit) {
		this.bypayProfit = bypayProfit;
	}
	public String getTractFee() {
		return tractFee;
	}
	public void setTractFee(String tractFee) {
		this.tractFee = tractFee;
	}
	public String getTractBypayProfit() {
		return tractBypayProfit;
	}
	public void setTractBypayProfit(String tractBypayProfit) {
		this.tractBypayProfit = tractBypayProfit;
	}
	public String getTractAcqbankProfit() {
		return tractAcqbankProfit;
	}
	public void setTractAcqbankProfit(String tractAcqbankProfit) {
		this.tractAcqbankProfit = tractAcqbankProfit;
	}
	public String getTractCost() {
		return tractCost;
	}
	public void setTractCost(String tractCost) {
		this.tractCost = tractCost;
	}
	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	
	
}
