package com.bypay.domain;

/**
 * 子商户交易配置表
 * 
 * @author Wang
 * 
 */
public class SubMerTrans {
	private String subMerId; // 外键 SUB_MER_ID
	private String dispMerId; // 打印商户号 DISP_MER_ID
	private String disMerName; // 打印商户名称 DISP_MER_NAME
	private String terminalType; // 终端产品类型表 TERMINAL_TYPE
	// 多项集合以’|’分隔
	private String busType; // 交易类型表 BUS_TYPE
	// 多项集合以’|’分隔
	private String clearTime; // 清分周期，默认01 CLEAR_TIME
	// 01：T+1 02：T+0 03：T-N
	private String authStatus; // 实人认证状态 AUTH_STATUS
	// 0未认证 1认证成功 2认证失败 3处理中
	private String authTime; // 实人认证时间 AUTH_TIME
	private String createTime; // 创建时间 CREATE_TIME
	private String reserved; // RESERVED
	private String subMerTractR1; // SUB_MER_TRACT_R1 VARCHAR 100 是 可用通道，多通道已|分割
	// 扣率用
	private String subMerTractR2; // SUB_MER_TRACT_R2 VARCHAR 100 是 可用通道，多通道已|分割
	// 封顶用
	private String subMerTractR3; //SUB_MER_TRACT_R3 VARCHAR 100 是 可用通道，多通道已|分割    积分扣率通道
	
	
	private String t0Status; // T+0开通状态

	private String rateType; // 费率类型

	public String getSubMerTractR1() {
		return subMerTractR1;
	}

	public void setSubMerTractR1(String subMerTractR1) {
		this.subMerTractR1 = subMerTractR1;
	}

	public String getSubMerTractR2() {
		return subMerTractR2;
	}

	public void setSubMerTractR2(String subMerTractR2) {
		this.subMerTractR2 = subMerTractR2;
	}

	public String getSubMerId() {
		return subMerId;
	}

	public void setSubMerId(String subMerId) {
		this.subMerId = subMerId;
	}

	public String getDispMerId() {
		return dispMerId;
	}

	public void setDispMerId(String dispMerId) {
		this.dispMerId = dispMerId;
	}

	public String getDisMerName() {
		return disMerName;
	}

	public void setDisMerName(String disMerName) {
		this.disMerName = disMerName;
	}

	public String getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getClearTime() {
		return clearTime;
	}

	public void setClearTime(String clearTime) {
		this.clearTime = clearTime;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public String getAuthTime() {
		return authTime;
	}

	public void setAuthTime(String authTime) {
		this.authTime = authTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	public String getT0Status() {
		return t0Status;
	}

	public void setT0Status(String t0Status) {
		this.t0Status = t0Status;
	}

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public String getSubMerTractR3() {
		return subMerTractR3;
	}

	public void setSubMerTractR3(String subMerTractR3) {
		this.subMerTractR3 = subMerTractR3;
	}

	
}
