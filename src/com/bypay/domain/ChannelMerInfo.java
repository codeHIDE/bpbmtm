package com.bypay.domain;

import java.math.BigDecimal;

/**
 * 此类由MySQLToBean工具自动生成 备注(数据表的comment字段)：无备注信息 2015-06-09 05:09:27
 */

public class ChannelMerInfo {
	private Long chId;
	private String chName;
	private String chMerId;
	private String chTermId;
	private String chSeriId;
	private String chBatchId;
	private String chTermSeqId;
	private Integer chStat;
	private Integer chType;
	private String chAddId;
	private Integer chFeeId;
	private String oprId;
	private String provId;
	private String chZMK;
	private String chZPK;
	private String chZAK;
	private String chZEK;
	private String chEncKey;
	private String openDate;
	private BigDecimal transAmtPool;
	private String lastUpdTs;
	//其他表关联信息
	private Long areaId;
	private String areaName;//地区名称
	private String chMerName;//渠道地址
	private String merId;//商户id
	private String merName;//商户名称
	private String merchantNo;//商户号

	private String t0Fee;              //T0手续费
	private String t0Rate;             //T0费率
	private String rateType;           //费率类型
	
	public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public String getT0Fee() {
        return t0Fee;
    }

    public void setT0Fee(String t0Fee) {
        this.t0Fee = t0Fee;
    }

    public String getT0Rate() {
        return t0Rate;
    }

    public void setT0Rate(String t0Rate) {
        this.t0Rate = t0Rate;
    }

    public Integer getChType() {
		return chType;
	}

	public void setChType(Integer chType) {
		this.chType = chType;
	}
	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getChMerName() {
		return chMerName;
	}

	public void setChMerName(String chMerName) {
		this.chMerName = chMerName;
	}

	public Long getChId() {
		return chId;
	}

	public void setChId(Long chId) {
		this.chId = chId;
	}

	public String getChName() {
		return this.chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	public String getChMerId() {
		return this.chMerId;
	}

	public void setChMerId(String chMerId) {
		this.chMerId = chMerId;
	}

	public String getChTermId() {
		return this.chTermId;
	}

	public void setChTermId(String chTermId) {
		this.chTermId = chTermId;
	}

	public String getChBatchId() {
		return this.chBatchId;
	}

	public void setChBatchId(String chBatchId) {
		this.chBatchId = chBatchId;
	}

	public String getChTermSeqId() {
		return this.chTermSeqId;
	}

	public void setChTermSeqId(String chTermSeqId) {
		this.chTermSeqId = chTermSeqId;
	}

	public Integer getChStat() {
		return this.chStat;
	}

	public void setChStat(Integer chStat) {
		this.chStat = chStat;
	}

	public String getChAddId() {
		return this.chAddId;
	}

	public void setChAddId(String chAddId) {
		this.chAddId = chAddId;
	}

	public Integer getChFeeId() {
		return this.chFeeId;
	}

	public void setChFeeId(Integer chFeeId) {
		this.chFeeId = chFeeId;
	}
	
	public String getOprId() {
		return oprId;
	}

	public void setOprId(String oprId) {
		this.oprId = oprId;
	}

	public String getProvId() {
		return this.provId;
	}

	public void setProvId(String provId) {
		this.provId = provId;
	}

	public String getChZMK() {
		return this.chZMK;
	}

	public void setChZMK(String chZMK) {
		this.chZMK = chZMK;
	}

	public String getChZPK() {
		return this.chZPK;
	}

	public void setChZPK(String chZPK) {
		this.chZPK = chZPK;
	}

	public String getChZAK() {
		return this.chZAK;
	}

	public void setChZAK(String chZAK) {
		this.chZAK = chZAK;
	}

	public String getChZEK() {
		return this.chZEK;
	}

	public void setChZEK(String chZEK) {
		this.chZEK = chZEK;
	}

	public String getOpenDate() {
		return this.openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public BigDecimal getTransAmtPool() {
		return this.transAmtPool;
	}

	public void setTransAmtPool(BigDecimal transAmtPool) {
		this.transAmtPool = transAmtPool;
	}

	public String getLastUpdTs() {
		return this.lastUpdTs;
	}

	public void setLastUpdTs(String lastUpdTs) {
		this.lastUpdTs = lastUpdTs;
	}

	public String getChEncKey() {
		return chEncKey;
	}

	public void setChEncKey(String chEncKey) {
		this.chEncKey = chEncKey;
	}

	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getChSeriId() {
		return chSeriId;
	}

	public void setChSeriId(String chSeriId) {
		this.chSeriId = chSeriId;
	}

}