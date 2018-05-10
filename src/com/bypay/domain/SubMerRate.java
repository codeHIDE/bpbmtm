package com.bypay.domain;

/**
 * 子商户交易费率表
 * 
 * @author Wang
 * 
 */
public class SubMerRate {
	private String subMerId;// SUB_MER_ID BIGINT 11 否 外键
	private String rateType;// RATE_TYPE VARCHAR 2 　　　否 费率类型
	// 01：扣率/ /02：封顶/ /03：T+0扣率/ /04：T+0封顶
	private String teransRate;// TRANS_RATE VARCHAR 10 否 交易费率（单位％）
	private String transHighestFee;// TRANS_HIGHEST_FEE VARCHAR 10 是 封顶值
	private String lowestFee;// LOWEST_FEE VARCHAR 10 是 封底值
	private String agentL1Rate;// AGENT_L1_RATE VARCHAR 10 是 一级代理费率（单位%）
	private String agentL1HighestFee;// AGENT_L1_HIGHEST_FEE VARCHAR 10 是
										// 一级代理封顶值
	private String agentL1ProfitRate;// AGENT_L1_PROFIT_RATE VARCHAR 10 是
										// 一级代理分润占比
	// （单位%）
	private String agentL2Rate;// /AGENT_L2_RATE VARCHAR 10 是 二级代理费率（单位%）
	private String agentL2HigestFee;// AGENT_L2_HIGHEST_FEE VARCHAR 10 是 二级代理封顶值
	private String agentL2ProfitRate;// AGENT_L2_PROFIT_RATE VARCHAR 10 是
										// 二级代理分润占比
	// （单位%）
	private String createTime;// CREATE_TIME VARCHAR 20 否 创建时间
	private String status;// STATUS VARCHAR 1 否 状态
	private String subMerTract;// 可用通道，多通道已|分割
	private String reserued;// RESERVED VARCHAR 50 是
	private String newRateType;// 用于存储修改后的费率类型
	private String subMerName;

	private String agentL1RateH;
	private String agentL2RateH;
	private String agentL1NoTop;
	private String agentL2NoTop;
	private String transRateH;
	
	private String transRateNoTop;
	
	private String agentL3Rate;// AGENT_L3_RATE VARCHAR 10 是 3级代理费率（单位%）
	private String agentL3HighestFee;// AGENT_L3_HIGHEST_FEE VARCHAR 10 是
										// 3级代理封顶值
	private String agentL3ProfitRate;// AGENT_L3_PROFIT_RATE VARCHAR 10 是
	private String agentL3RateH;
	private String agentL3NoTop;
	
	private String agentL4Rate;// AGENT_L3_RATE VARCHAR 10 是 3级代理费率（单位%）
	private String agentL4HighestFee;// AGENT_L3_HIGHEST_FEE VARCHAR 10 是
										// 3级代理封顶值
	private String agentL4ProfitRate;// AGENT_L4_PROFIT_RATE VARCHAR 10 是
	private String agentL4RateH;
	private String agentL4NoTop;
	
	private String agentL5Rate;///AGENT_L2_RATE VARCHAR 10  是   二级代理费率（单位%）
	private String agentL6Rate;///AGENT_L2_RATE VARCHAR 10  是   二级代理费率（单位%）
	private String agentL7Rate;///AGENT_L2_RATE VARCHAR 10  是   二级代理费率（单位%）
	private String agentL8Rate;///AGENT_L2_RATE VARCHAR 10  是   二级代理费率（单位%）
	private String agentL9Rate;///AGENT_L2_RATE VARCHAR 10  是   二级代理费率（单位%）
	private String agentL10Rate;///AGENT_L2_RATE VARCHAR 10  是   二级代理费率（单位%）
 
	public String getAgentL5Rate() {
        return agentL5Rate;
    }

    public void setAgentL5Rate(String agentL5Rate) {
        this.agentL5Rate = agentL5Rate;
    }

    public String getAgentL6Rate() {
        return agentL6Rate;
    }

    public void setAgentL6Rate(String agentL6Rate) {
        this.agentL6Rate = agentL6Rate;
    }

    public String getAgentL7Rate() {
        return agentL7Rate;
    }

    public void setAgentL7Rate(String agentL7Rate) {
        this.agentL7Rate = agentL7Rate;
    }

    public String getAgentL8Rate() {
        return agentL8Rate;
    }

    public void setAgentL8Rate(String agentL8Rate) {
        this.agentL8Rate = agentL8Rate;
    }

    public String getAgentL9Rate() {
        return agentL9Rate;
    }

    public void setAgentL9Rate(String agentL9Rate) {
        this.agentL9Rate = agentL9Rate;
    }

    public String getAgentL10Rate() {
        return agentL10Rate;
    }

    public void setAgentL10Rate(String agentL10Rate) {
        this.agentL10Rate = agentL10Rate;
    }

    public String getAgentL3Rate() {
		return agentL3Rate;
	}

	public void setAgentL3Rate(String agentL3Rate) {
		this.agentL3Rate = agentL3Rate;
	}

	public String getAgentL3HighestFee() {
		return agentL3HighestFee;
	}

	public void setAgentL3HighestFee(String agentL3HighestFee) {
		this.agentL3HighestFee = agentL3HighestFee;
	}

	public String getAgentL3ProfitRate() {
		return agentL3ProfitRate;
	}

	public void setAgentL3ProfitRate(String agentL3ProfitRate) {
		this.agentL3ProfitRate = agentL3ProfitRate;
	}

	public String getAgentL3RateH() {
		return agentL3RateH;
	}

	public void setAgentL3RateH(String agentL3RateH) {
		this.agentL3RateH = agentL3RateH;
	}

	public String getAgentL3NoTop() {
		return agentL3NoTop;
	}

	public void setAgentL3NoTop(String agentL3NoTop) {
		this.agentL3NoTop = agentL3NoTop;
	}

	public String getAgentL4Rate() {
		return agentL4Rate;
	}

	public void setAgentL4Rate(String agentL4Rate) {
		this.agentL4Rate = agentL4Rate;
	}

	public String getAgentL4HighestFee() {
		return agentL4HighestFee;
	}

	public void setAgentL4HighestFee(String agentL4HighestFee) {
		this.agentL4HighestFee = agentL4HighestFee;
	}

	public String getAgentL4ProfitRate() {
		return agentL4ProfitRate;
	}

	public void setAgentL4ProfitRate(String agentL4ProfitRate) {
		this.agentL4ProfitRate = agentL4ProfitRate;
	}

	public String getAgentL4RateH() {
		return agentL4RateH;
	}

	public void setAgentL4RateH(String agentL4RateH) {
		this.agentL4RateH = agentL4RateH;
	}

	public String getAgentL4NoTop() {
		return agentL4NoTop;
	}

	public void setAgentL4NoTop(String agentL4NoTop) {
		this.agentL4NoTop = agentL4NoTop;
	}

	public String getAgentL2NoTop() {
		return agentL2NoTop;
	}

	public void setAgentL2NoTop(String agentL2NoTop) {
		this.agentL2NoTop = agentL2NoTop;
	}

	public String getAgentL1NoTop() {
		return agentL1NoTop;
	}

	public void setAgentL1NoTop(String agentL1NoTop) {
		this.agentL1NoTop = agentL1NoTop;
	}

	public String getSubMerTract() {
		return subMerTract;
	}

	public void setSubMerTract(String subMerTract) {
		this.subMerTract = subMerTract;
	}

	public String getSubMerName() {
		return subMerName;
	}

	public void setSubMerName(String subMerName) {
		this.subMerName = subMerName;
	}

	public String getNewRateType() {
		return newRateType;
	}

	public void setNewRateType(String newRateType) {
		this.newRateType = newRateType;
	}

	public String getSubMerId() {
		return subMerId;
	}

	public void setSubMerId(String subMerId) {
		this.subMerId = subMerId;
	}

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public String getTeransRate() {
		return teransRate;
	}

	public void setTeransRate(String teransRate) {
		this.teransRate = teransRate;
	}

	public String getTransHighestFee() {
		return transHighestFee;
	}

	public void setTransHighestFee(String transHighestFee) {
		this.transHighestFee = transHighestFee;
	}

	public String getLowestFee() {
		return lowestFee;
	}

	public void setLowestFee(String lowestFee) {
		this.lowestFee = lowestFee;
	}

	public String getAgentL1Rate() {
		return agentL1Rate;
	}

	public void setAgentL1Rate(String agentL1Rate) {
		this.agentL1Rate = agentL1Rate;
	}

	public String getAgentL1HighestFee() {
		return agentL1HighestFee;
	}

	public void setAgentL1HighestFee(String agentL1HighestFee) {
		this.agentL1HighestFee = agentL1HighestFee;
	}

	public String getAgentL1ProfitRate() {
		return agentL1ProfitRate;
	}

	public void setAgentL1ProfitRate(String agentProfitRate) {
		this.agentL1ProfitRate = agentProfitRate;
	}

	public String getAgentL2Rate() {
		return agentL2Rate;
	}

	public void setAgentL2Rate(String agentL2Rate) {
		this.agentL2Rate = agentL2Rate;
	}

	public String getAgentL2HigestFee() {
		return agentL2HigestFee;
	}

	public void setAgentL2HigestFee(String agentL2HigestFee) {
		this.agentL2HigestFee = agentL2HigestFee;
	}

	public String getAgentL2ProfitRate() {
		return agentL2ProfitRate;
	}

	public void setAgentL2ProfitRate(String agentL2ProfitRate) {
		this.agentL2ProfitRate = agentL2ProfitRate;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReserued() {
		return reserued;
	}

	public void setReserued(String reserued) {
		this.reserued = reserued;
	}

	public String getAgentL1RateH() {
		return agentL1RateH;
	}

	public void setAgentL1RateH(String agentL1RateH) {
		this.agentL1RateH = agentL1RateH;
	}

	public String getAgentL2RateH() {
		return agentL2RateH;
	}

	public void setAgentL2RateH(String agentL2RateH) {
		this.agentL2RateH = agentL2RateH;
	}

	public String getTransRateH() {
		return transRateH;
	}

	public void setTransRateH(String transRateH) {
		this.transRateH = transRateH;
	}

	public String getTransRateNoTop() {
		return transRateNoTop;
	}

	public void setTransRateNoTop(String transRateNoTop) {
		this.transRateNoTop = transRateNoTop;
	}

	
}
