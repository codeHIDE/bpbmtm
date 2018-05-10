package com.bypay.domain;

public class StatisticsInfo {

	private String createTime;//创建时间
	private String countNum;//各类数据总数
	
	private int debitCard;//借记卡
	private int creditCard;//信用卡
	private String respCode;//返回码
	private String respDesc;//返回描述
	
	private String sumTransAmt;//总交易额
	private String sumTransFee;//总交易手续费
	

	private String agentId;//代理商ID
	private String agentName;//代理商名
	private String level;//代理商级别 
	private String transFee;//交易手续费
	private String merAmt;//交易金额
	private String merCapital;//交易本金
	private String bypayProfit;//平台分润
	private String merProfit;//机构分润
	private String agentProfit;//代理商利润
	private String agent1Profit;//一级代理商利润
	private String agent2Profit;//二级代理商利润
	private String merName;    // MER_NAME
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCountNum() {
		return countNum;
	}
	public void setCountNum(String countNum) {
		this.countNum = countNum;
	}
	public int getDebitCard() {
		return debitCard;
	}
	public void setDebitCard(int debitCard) {
		this.debitCard = debitCard;
	}
	public int getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(int creditCard) {
		this.creditCard = creditCard;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespDesc() {
		return respDesc;
	}
	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}
	public String getSumTransAmt() {
		return sumTransAmt;
	}
	public void setSumTransAmt(String sumTransAmt) {
		this.sumTransAmt = sumTransAmt;
	}
	public String getSumTransFee() {
		return sumTransFee;
	}
	public void setSumTransFee(String sumTransFee) {
		this.sumTransFee = sumTransFee;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getTransFee() {
		return transFee;
	}
	public void setTransFee(String transFee) {
		this.transFee = transFee;
	}
	public String getMerAmt() {
		return merAmt;
	}
	public void setMerAmt(String merAmt) {
		this.merAmt = merAmt;
	}
	public String getMerCapital() {
		return merCapital;
	}
	public void setMerCapital(String merCapital) {
		this.merCapital = merCapital;
	}
	public String getBypayProfit() {
		return bypayProfit;
	}
	public void setBypayProfit(String bypayProfit) {
		this.bypayProfit = bypayProfit;
	}
	public String getMerProfit() {
		return merProfit;
	}
	public void setMerProfit(String merProfit) {
		this.merProfit = merProfit;
	}
	public String getAgentProfit() {
		if("1".equals(level)) {
			agentProfit = agent1Profit;
		}
		if("2".equals(level)) {
			agentProfit = agent2Profit;
		}
		return agentProfit;
	}
	public void setAgentProfit(String agentProfit) {
		this.agentProfit = agentProfit;
	}
	public String getAgent1Profit() {
		return agent1Profit;
	}
	public void setAgent1Profit(String agent1Profit) {
		this.agent1Profit = agent1Profit;
	}
	public String getAgent2Profit() {
		return agent2Profit;
	}
	public void setAgent2Profit(String agent2Profit) {
		this.agent2Profit = agent2Profit;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	
	
}
