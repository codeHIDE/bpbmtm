package com.bypay.domain;

public class TractInfo {
	private String tractId;  				//	TRACT_ID		自增长(6位)
	private String tractName;				//	TRACT_NAME				通道名
	private String	transMerId;				//	TRANS_MER_ID			交易商户号
	private String	tractAgency;				//	TRACT_AGENCY		通道机构					
	private String	ratesType;					//	RATES_TYPE			费率类型	01扣率02封顶
	private String	acqAgencyId;				//	ACQ_AGENCY_ID		收单机构号
	private String	acqAgencyName;		//	ACQ_AGENCY_NAME		收单机构名
	private String	payTract;					//	PAY_TRACT			支付通道
	private String	tractRate;					//	TRACT_RATE			费率     
	private String	tractHighestFee;		//	TRACT_HIGHEST__FEE		封顶值   0
	private String	creditRateCost;			//	CREDIT_RATE_COST		信用卡成本费率
	private String	debitRateCost;			//	DEBIT_RATE_COST			借记卡成本费率
	private String	creditHighestCost;	//	CREDIT_HIGHEST_COST	信用卡封顶成本 
	private String	debitHighestCost;		//	DEBIT_HIGHEST_COST		借记卡封顶成本	
	private String	lowestCost;				//	LOWEST_COST		最低成本	0
	private String	bypayProfit;				//	BYPAY_PROFIT平台分润比   				
	private String	status;							//	STATUS	状态0　未使用1　正在使用2　暂停kong？
	private String	createTime;				//	CREATE_TIME		创建时间					kong？
	private String	perCardQuota;			//	PER_CARD_ QUOTA	单卡单笔限额 
	private String	cardQuota;					//	CARD_ QUOTA	单卡单日限额 				
	private String	cardCount;					//	CARD_COUNT	单卡单日次数 				
	private String	remark;						//	REMARK		备注		
	private String  nightFlag;				//NIGHT_FLAG	0 不支持	1 支持
	private String	reserved;					//	RESERVED	扩展		
	private String transTimeBegin;		//RANS_TIME_BEGIN		交易开始时间
	private String transTimeEnd;			//TRANS_TIME_END	交易结束时间
	private String tractFlag;		//TRACT_FLAG  默认0 小额通道	1 大额通道	
	private String cardFlag;
	private String mcc;							//MCC			代码
	private String integral;						//INTEGRAL	状态 0　无 1　积分通道
	private String tractQuota;				//TRACT_QUOTA		通道限额
	private String payMerId;				//代付商户号
	private String payTerId;				//代付终端号
	private String tractType;				//通道类别 1轮循2点对点 
	
	public String getTractType() {
		return tractType;
	}

	public void setTractType(String tractType) {
		this.tractType = tractType;
	}

	public String getPayMerId() {
		return payMerId;
	}

	public void setPayMerId(String payMerId) {
		this.payMerId = payMerId;
	}

	public String getPayTerId() {
		return payTerId;
	}

	public void setPayTerId(String payTerId) {
		this.payTerId = payTerId;
	}

	private String spId;			//SPID
	
	private String terminalId;		//TERMINAL_ID
	
	public String getCardFlag() {
		return cardFlag;
	}

	public void setCardFlag(String cardFlag) {
		this.cardFlag = cardFlag;
	}

	public String getSpId() {
		return spId;
	}

	public void setSpId(String spId) {
		this.spId = spId;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}


	public String getTractFlag() {
		return tractFlag;
	}

	public void setTractFlag(String tractFlag) {
		this.tractFlag = tractFlag;
	}

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getIntegral() {
		return integral;
	}

	public String getNightFlag() {
		return nightFlag;
	}

	public void setNightFlag(String nightFlag) {
		this.nightFlag = nightFlag;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}

	public String getTractQuota() {
		return tractQuota;
	}

	public void setTractQuota(String tractQuota) {
		this.tractQuota = tractQuota;
	}

	public String getTractId() {
		return tractId;
	}

	public void setTractId(String tractId) {
		this.tractId = tractId;
	}

	public String getTractName() {
		return tractName;
	}

	public void setTractName(String tractName) {
		this.tractName = tractName;
	}

	public String getTransMerId() {
		return transMerId;
	}

	public void setTransMerId(String transMerId) {
		this.transMerId = transMerId;
	}

	public String getTractAgency() {
		return tractAgency;
	}

	public void setTractAgency(String tractAgency) {
		this.tractAgency = tractAgency;
	}

	public String getRatesType() {
		return ratesType;
	}

	public void setRatesType(String ratesType) {
		this.ratesType = ratesType;
	}

	public String getAcqAgencyId() {
		return acqAgencyId;
	}

	public void setAcqAgencyId(String acqAgencyId) {
		this.acqAgencyId = acqAgencyId;
	}

	public String getAcqAgencyName() {
		return acqAgencyName;
	}

	public void setAcqAgencyName(String acqAgencyName) {
		this.acqAgencyName = acqAgencyName;
	}

	public String getPayTract() {
		return payTract;
	}

	public void setPayTract(String payTract) {
		this.payTract = payTract;
	}

	public String getTractRate() {
		return tractRate;
	}

	public void setTractRate(String tractRate) {
		this.tractRate = tractRate;
	}

	public String getTractHighestFee() {
		return tractHighestFee;
	}

	public void setTractHighestFee(String tractHighestFee) {
		this.tractHighestFee = tractHighestFee;
	}

	public String getCreditRateCost() {
		return creditRateCost;
	}

	public void setCreditRateCost(String creditRateCost) {
		this.creditRateCost = creditRateCost;
	}

	public String getDebitRateCost() {
		return debitRateCost;
	}

	public void setDebitRateCost(String debitRateCost) {
		this.debitRateCost = debitRateCost;
	}

	public String getCreditHighestCost() {
		return creditHighestCost;
	}

	public void setCreditHighestCost(String creditHighestCost) {
		this.creditHighestCost = creditHighestCost;
	}

	public String getDebitHighestCost() {
		return debitHighestCost;
	}

	public void setDebitHighestCost(String debitHighestCost) {
		this.debitHighestCost = debitHighestCost;
	}

	public String getLowestCost() {
		return lowestCost;
	}

	public void setLowestCost(String lowestCost) {
		this.lowestCost = lowestCost;
	}

	public String getBypayProfit() {
		return bypayProfit;
	}

	public void setBypayProfit(String bypayProfit) {
		this.bypayProfit = bypayProfit;
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

	public String getPerCardQuota() {
		return perCardQuota;
	}

	public void setPerCardQuota(String perCardQuota) {
		this.perCardQuota = perCardQuota;
	}

	public String getCardQuota() {
		return cardQuota;
	}

	public void setCardQuota(String cardQuota) {
		this.cardQuota = cardQuota;
	}

	public String getCardCount() {
		return cardCount;
	}

	public void setCardCount(String cardCount) {
		this.cardCount = cardCount;
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

	public String getTransTimeBegin() {
		return transTimeBegin;
	}

	public void setTransTimeBegin(String transTimeBegin) {
		this.transTimeBegin = transTimeBegin;
	}

	public String getTransTimeEnd() {
		return transTimeEnd;
	}

	public void setTransTimeEnd(String transTimeEnd) {
		this.transTimeEnd = transTimeEnd;
	}
}
