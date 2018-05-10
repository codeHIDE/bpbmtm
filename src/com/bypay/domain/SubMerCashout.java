package com.bypay.domain;

import java.io.Serializable;

public class SubMerCashout implements Serializable {

	/**
   * 
   */
	private static final long serialVersionUID = -5786331393737401705L;

	private String id;

	private String subMerId;

	private String merSysId;

	private String orderAmt;

	private String orderStatus;
	/*if(value=="0"){
		return "申请";
	}else if(value=="1"){
		return "等待代发";
	}else if(value=="2"){
		return "代发成功";
	}else if(value=="3"){
		return "当日代发失败";
	}else if(value=="4"){
		return "次日代发失败";
	}else if(value=="5"){
		return "已T+1清分";
	}else if(value=="6"){
		return "钱包消费";
	}else if(value=="9"){
		return "代发处理中";
	}*/

	private String createTime;

	private String finishTime;

	private String TransTime;

	private String transId;

	private String TransQid;

	private String TransFee;

	private String batchId;

	private String reserved1;

	private String reserved2;

	private String reserved3;
	
	private String merProfitRate;
	
	private String t0MerGain;
	
	private String t0MerRate;
	
	private String bankName;
	
	private String settAccountNo; //结算账号

	private String grade;  //级别

	private String settAccountName; //计算账户名
	
	private String lineNum; //联行号
	
	private String openBank; //开户行
	
	private String settAgency; //银行代码
	
	private String orderId;		//原交易流水

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getMerProfitRate() {
		return merProfitRate;
	}

	public void setMerProfitRate(String merProfitRate) {
		this.merProfitRate = merProfitRate;
	}

	public String getT0MerGain() {
		return t0MerGain;
	}

	public void setT0MerGain(String t0MerGain) {
		this.t0MerGain = t0MerGain;
	}

	public String getT0MerRate() {
		return t0MerRate;
	}

	public void setT0MerRate(String t0MerRate) {
		this.t0MerRate = t0MerRate;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getSettAccountNo() {
		return settAccountNo;
	}

	public void setSettAccountNo(String settAccountNo) {
		this.settAccountNo = settAccountNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubMerId() {
		return subMerId;
	}

	public void setSubMerId(String subMerId) {
		this.subMerId = subMerId;
	}

	public String getMerSysId() {
		return merSysId;
	}

	public void setMerSysId(String merSysId) {
		this.merSysId = merSysId;
	}

	public String getOrderAmt() {
		return orderAmt;
	}

	public void setOrderAmt(String orderAmt) {
		this.orderAmt = orderAmt;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public String getTransTime() {
		return TransTime;
	}

	public void setTransTime(String transTime) {
		TransTime = transTime;
	}

	public String getTransQid() {
		return TransQid;
	}

	public void setTransQid(String transQid) {
		TransQid = transQid;
	}

	public String getTransFee() {
		return TransFee;
	}

	public void setTransFee(String transFee) {
		TransFee = transFee;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSettAccountName() {
		return settAccountName;
	}

	public void setSettAccountName(String settAccountName) {
		this.settAccountName = settAccountName;
	}

	public String getLineNum() {
		return lineNum;
	}

	public void setLineNum(String lineNum) {
		this.lineNum = lineNum;
	}

	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	public String getSettAgency() {
		return settAgency;
	}

	public void setSettAgency(String settAgency) {
		this.settAgency = settAgency;
	}

}
