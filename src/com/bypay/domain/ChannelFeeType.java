package com.bypay.domain;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 渠道费率表
 * @author WEIXL
 */
public class ChannelFeeType {
	private Long chFeeId;
	private Long feeType;
	private String feeTypeName;
	private BigDecimal feeRate;
	private BigDecimal topFee;
	private String lastUpdTs;
	
	public Long getChFeeId() {
		return chFeeId;
	}
	public void setChFeeId(Long chFeeId) {
		this.chFeeId = chFeeId;
	}
	public Long getFeeType() {
		return feeType;
	}
	public void setFeeType(Long feeType) {
		this.feeType = feeType;
	}
	public String getFeeTypeName() {
		return feeTypeName;
	}
	public void setFeeTypeName(String feeTypeName) {
		this.feeTypeName = feeTypeName;
	}
	public BigDecimal getFeeRate() {
		return feeRate;
	}
	public void setFeeRate(BigDecimal feeRate) {
		this.feeRate = feeRate;
	}
	public BigDecimal getTopFee() {
		return topFee;
	}
	public void setTopFee(BigDecimal topFee) {
		this.topFee = topFee;
	}
	public String getLastUpdTs() {
		return lastUpdTs;
	}
	public void setLastUpdTs(String lastUpdTs) {
		this.lastUpdTs = lastUpdTs;
	}
	
	
}
