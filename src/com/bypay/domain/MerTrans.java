package com.bypay.domain;

public class MerTrans {
	private String merSysId; // MER_SYS_ID BIGINT 1 否 外键
	private String transKey;; // TRANS_KEY VARCHAR 50 是 交易密钥
	private String publicKey; // PUBLIC_KEY TEXT 是 加密公钥
	private String privateKey; // PRIVATE_KEY TEXT 是 加密私钥
	private String rules; // RULES VARCHAR 500 是 通道规则
	private String lowsetTransAmt; // LOWEST_TRANS_AMT VARCHAR 10 否 最低交易金额
	private String terminalType; // TERMINAL_TYPE VARCHAR 30 否 终端产品类型表
									// 多项集合以’|’分隔
	private String busType; // BUS_TYPE VARCHAR 30 否 交易类型表 多项集合以’|’分隔
	private String actionList; // ACTION_LIST VARCHAR 20 是 用于终端可交易类型的配置，多项以”|”分隔
	private String autoApprove; // AUTO_APPROVE VARCHAR 1 否 是否自动审核 0：未开通 1：已开通
	private String authStatus; // AUTH_STATUS VARCHAR 1 否 子商户是否需要实人认证的状态 0：未开通
	// 1：已开通
	private String t0Status; // T0_STATUS VARCHAR 1 否 T+0业务状态 0：未开通 1：已开通
	private String clearType; // CLEAR_TYPE VARCHAR 1 否 自动代清分 0 不清分 1 代清分给子商户 2
	// 清分给平台
	private String clearTract; // CLEAR_TRACT VARCHAR 10 是 当AUTO_CLEAR为1时，此处不为空
	private String transNotifyStatus; // TRANS_NOTIFY_STATUS VARCHAR 1 否 交易同步状态
										// 0
	// 不同步 1 同步
	private String transNotifyUrl; // TRANS_NOTIFY_URL VARCHAR 100 是 同步URL地址
	private String transNotifyActionList; // TRANS_NOTIFY_ACTION_LIST VARCHAR 20
											// 是
	// 同步类型列表,多项以”|”分隔
	private String basicRate; // BASIC_RATE VARCHAR 5 是 子商户基础费率 (单位：百分比)
	private String basicRate2; // BASIC_RATE2 VARCHAR 5 是 基础费率2 可用于封顶 (单位：百分比)
	private String basicHighestFee; // BASIC_HIGHEST_FEE VARCHAR 5 是 基础封顶费用
	private String basicPerCardQuota; // BASIC_PER_CARD_QUOTA VARCHAR 10 是
										// 基础单笔限额
	private String basicCardQuota; // BASIC_CARD_QUOTA VARCHAR 10 是 基础单日限额
	private String basicCardCount; // BAISC_CARD_COUNT VARCHAR 10 是 基础单日次数
	private String basicTerminalQuota; // BASIC_TERMINAL_QUOTA VARCHAR 10 是
	// 基础单终端单日限额
	private String merRate1;// MER_RATE_1 VARCHAR 10 是 机构商费率 扣率
	private String merRate2;// MER_RATE_2 VARCHAR 10 是 机构商费率 封顶
	private String merHighestFee;// MER_HIGHEST_FEE VARCHAR 10 是 机构商封顶值
	private String merLowestFee;// MER_LOWEST_FEE VARCHAR 10 是 机构商封低值
	private String merProfitRate;// MER_PROFIT_RATE VARCHAR 10 是 机构商分润比
	private String reserved; // RESERVED VARCHAR 50 是 扩展
	private String basicMcc;
	private String basicRegion;
	private String rateType;// 费率类型
	private String dispName;// 显示名称
	private String t0MerRate; // T0费率
	private String d1MerRate; // D1费率
	private String t0MerProfit; // 服务商T0收益
	private String d1MerProfit; // 服务商D+1收益

	private String merRateNoTop; // 积分扣率

	public String getBasicMcc() {
		return basicMcc;
	}

	public void setBasicMcc(String basicMcc) {
		this.basicMcc = basicMcc;
	}

	public String getBasicRegion() {
		return basicRegion;
	}

	public void setBasicRegion(String basicRegion) {
		this.basicRegion = basicRegion;
	}

	public String getMerRate1() {
		return merRate1;
	}

	public void setMerRate1(String merRate1) {
		this.merRate1 = merRate1;
	}

	public String getMerRate2() {
		return merRate2;
	}

	public void setMerRate2(String merRate2) {
		this.merRate2 = merRate2;
	}

	public String getMerHighestFee() {
		return merHighestFee;
	}

	public void setMerHighestFee(String merHighestFee) {
		this.merHighestFee = merHighestFee;
	}

	public String getMerLowestFee() {
		return merLowestFee;
	}

	public void setMerLowestFee(String merLowestFee) {
		this.merLowestFee = merLowestFee;
	}

	public String getMerProfitRate() {
		return merProfitRate;
	}

	public void setMerProfitRate(String merProfitRate) {
		this.merProfitRate = merProfitRate;
	}

	public String getDispName() {
		return dispName;
	}

	public void setDispName(String dispName) {
		this.dispName = dispName;
	}

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public String getMerSysId() {
		return merSysId;
	}

	public void setMerSysId(String merSysId) {
		this.merSysId = merSysId;
	}

	public String getTransKey() {
		return transKey;
	}

	public void setTransKey(String transKey) {
		this.transKey = transKey;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public String getLowsetTransAmt() {
		return lowsetTransAmt;
	}

	public void setLowsetTransAmt(String lowsetTransAmt) {
		this.lowsetTransAmt = lowsetTransAmt;
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

	public String getActionList() {
		return actionList;
	}

	public void setActionList(String actionList) {
		this.actionList = actionList;
	}

	public String getAutoApprove() {
		return autoApprove;
	}

	public void setAutoApprove(String autoApprove) {
		this.autoApprove = autoApprove;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public String getT0Status() {
		return t0Status;
	}

	public void setT0Status(String t0Status) {
		this.t0Status = t0Status;
	}

	public String getClearType() {
		return clearType;
	}

	public void setClearType(String clearType) {
		this.clearType = clearType;
	}

	public String getClearTract() {
		return clearTract;
	}

	public void setClearTract(String clearTract) {
		this.clearTract = clearTract;
	}

	public String getTransNotifyStatus() {
		return transNotifyStatus;
	}

	public void setTransNotifyStatus(String transNotifyStatus) {
		this.transNotifyStatus = transNotifyStatus;
	}

	public String getTransNotifyUrl() {
		return transNotifyUrl;
	}

	public void setTransNotifyUrl(String transNotifyUrl) {
		this.transNotifyUrl = transNotifyUrl;
	}

	public String getTransNotifyActionList() {
		return transNotifyActionList;
	}

	public void setTransNotifyActionList(String transNotifyActionList) {
		this.transNotifyActionList = transNotifyActionList;
	}

	public String getBasicRate() {
		return basicRate;
	}

	public void setBasicRate(String basicRate) {
		this.basicRate = basicRate;
	}

	public String getBasicRate2() {
		return basicRate2;
	}

	public void setBasicRate2(String basicRate2) {
		this.basicRate2 = basicRate2;
	}

	public String getBasicHighestFee() {
		return basicHighestFee;
	}

	public void setBasicHighestFee(String basicHighestFee) {
		this.basicHighestFee = basicHighestFee;
	}

	public String getBasicPerCardQuota() {
		return basicPerCardQuota;
	}

	public void setBasicPerCardQuota(String basicPerCardQuota) {
		this.basicPerCardQuota = basicPerCardQuota;
	}

	public String getBasicCardQuota() {
		return basicCardQuota;
	}

	public void setBasicCardQuota(String basicCardQuota) {
		this.basicCardQuota = basicCardQuota;
	}

	public String getBasicCardCount() {
		return basicCardCount;
	}

	public void setBasicCardCount(String basicCardCount) {
		this.basicCardCount = basicCardCount;
	}

	public String getBasicTerminalQuota() {
		return basicTerminalQuota;
	}

	public void setBasicTerminalQuota(String basicTerminalQuota) {
		this.basicTerminalQuota = basicTerminalQuota;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	public String getT0MerRate() {
		return t0MerRate;
	}

	public void setT0MerRate(String t0MerRate) {
		this.t0MerRate = t0MerRate;
	}

	public String getD1MerRate() {
		return d1MerRate;
	}

	public void setD1MerRate(String d1MerRate) {
		this.d1MerRate = d1MerRate;
	}

	public String getT0MerProfit() {
		return t0MerProfit;
	}

	public void setT0MerProfit(String t0MerProfit) {
		this.t0MerProfit = t0MerProfit;
	}

	public String getD1MerProfit() {
		return d1MerProfit;
	}

	public void setD1MerProfit(String d1MerProfit) {
		this.d1MerProfit = d1MerProfit;
	}

	public String getMerRateNoTop() {
		return merRateNoTop;
	}

	public void setMerRateNoTop(String merRateNoTop) {
		this.merRateNoTop = merRateNoTop;
	}

}
