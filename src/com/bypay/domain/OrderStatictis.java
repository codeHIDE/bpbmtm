package com.bypay.domain;

public class OrderStatictis {
	private String orderId;//	ORDER_ID	BIGINT	20	否	订单号
	private String serialNo;//	SERIAL_NO	BIGINT	20	否	自增长
	private String subMerId;//	SUB_MER_ID	BIGINT	20	否	子商户号
	private String merAmt;//	MER_AMT	BIGINT	20	否	交易金额
	private String transFee;//	TRANS_FEE	decimal(20,2)	10	否	交易手续费
	private String clearAmt;//	CLEAR_AMT	decimal(20,2)	30	否	清分金额
	private String settleDate;//	SETTLE_DATE	VARCHAR	10	是	结算日期
	private String transType;//	TRANS_TYPE	VARCHAR	2		交易类型
	//	01消费
	//	04消费撤销,
	//	05消费退款
	private String clearType;//	CLEAR_TYPE	VARCHAR	1	否	清分类型
	//	0：T+0
	//	1：T+1
	private String createTime;
	private String clearBatch;//	CLEAR_BATCH_NO	VARCHAR	10	是	清分批次号
	private String clearTract;//	CLEAR_TRACT	VARCHAR	10	是	清分通道
	private String clearTime;//	CLEAR_TIME	VARCHAR	20	是	清分时间
	private String agent3Profit;//	AGENT3_PROFIT	BIGINT	20	是	三级代理商利润
	private String reserved;//	RESERVED	VARCHAR	50	是	扩展
	private String status;		//取消清算标识 状态 1 已对平
	private String checkStatus;		//对账标识 1,平 2,不平
	
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
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
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getSubMerId() {
		return subMerId;
	}
	public void setSubMerId(String subMerId) {
		this.subMerId = subMerId;
	}
	public String getMerAmt() {
		return merAmt;
	}
	public void setMerAmt(String merAmt) {
		this.merAmt = merAmt;
	}
	public String getTransFee() {
		return transFee;
	}
	public void setTransFee(String transFee) {
		this.transFee = transFee;
	}
	public String getClearAmt() {
		return clearAmt;
	}
	public void setClearAmt(String clearAmt) {
		this.clearAmt = clearAmt;
	}
	public String getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getClearType() {
		return clearType;
	}
	public void setClearType(String clearType) {
		this.clearType = clearType;
	}
	public String getClearBatch() {
		return clearBatch;
	}
	public void setClearBatch(String clearBatch) {
		this.clearBatch = clearBatch;
	}
	public String getClearTract() {
		return clearTract;
	}
	public void setClearTract(String clearTract) {
		this.clearTract = clearTract;
	}
	public String getClearTime() {
		return clearTime;
	}
	public void setClearTime(String clearTime) {
		this.clearTime = clearTime;
	}
	public String getAgent3Profit() {
		return agent3Profit;
	}
	public void setAgent3Profit(String agent3Profit) {
		this.agent3Profit = agent3Profit;
	}
	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

}
