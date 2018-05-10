package com.bypay.domain;

public class BypayProfitStatictis {

	private int id;//自增长
	private String settleDate;//清算日期
	private String sumAmt;//总消费金额
	private String sumFeeAmt;//总消费手续费
	private String sumCount;//总消费订单笔数
	private String sumCamt;//总撤销金额
	private String sumFeeCamt;//总撤销手续费
	private String sumCCount;//总撤销订单笔数
	private String sumRamt;//总退款金额
	private String sumFeeRamt;//总退款手续费
	private String sumRCount;//总退款订单笔数
	private String createTime;//创建时间
	private String sumTractFee;//总通道手续费
	private String bypayProfit;//Bypay分润
	private String reserved1;//保留域1
	private String reserved2;//保留域2
	private String reserved3;//保留域3
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getSumFeeAmt() {
		return sumFeeAmt;
	}
	public void setSumFeeAmt(String sumFeeAmt) {
		this.sumFeeAmt = sumFeeAmt;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getSumTractFee() {
		return sumTractFee;
	}
	public void setSumTractFee(String sumTractFee) {
		this.sumTractFee = sumTractFee;
	}
	public String getBypayProfit() {
		return bypayProfit;
	}
	public void setBypayProfit(String bypayProfit) {
		this.bypayProfit = bypayProfit;
	}
	public String getReserved1() {
		return reserved1;
	}
	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}
	public String getReserved2() {
		return reserved2;
	}
	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}
	public String getReserved3() {
		return reserved3;
	}
	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}
	public String getSumCount() {
		return sumCount;
	}
	public void setSumCount(String sumCount) {
		this.sumCount = sumCount;
	}
	public String getSumCamt() {
		return sumCamt;
	}
	public void setSumCamt(String sumCamt) {
		this.sumCamt = sumCamt;
	}
	public String getSumFeeCamt() {
		return sumFeeCamt;
	}
	public void setSumFeeCamt(String sumFeeCamt) {
		this.sumFeeCamt = sumFeeCamt;
	}
	public String getSumCCount() {
		return sumCCount;
	}
	public void setSumCCount(String sumCCount) {
		this.sumCCount = sumCCount;
	}
	public String getSumRamt() {
		return sumRamt;
	}
	public void setSumRamt(String sumRamt) {
		this.sumRamt = sumRamt;
	}
	public String getSumFeeRamt() {
		return sumFeeRamt;
	}
	public void setSumFeeRamt(String sumFeeRamt) {
		this.sumFeeRamt = sumFeeRamt;
	}
	public String getSumRCount() {
		return sumRCount;
	}
	public void setSumRCount(String sumRCount) {
		this.sumRCount = sumRCount;
	}
	
}
