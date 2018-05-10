package com.bypay.domain;

public class SubMerTerminal {
	private int id;					//ID 序号，自增长
	private String tsn;		        //TSN
	private String factory;			//FACTORY厂商
	private String subMerId;		//SUB_MER_ID子商户号
	private String merSysId;		//MER_SYS_ID机构商号
	private String platMerId;		//PLAT_MER_ID平台商号
	private String version;			//VERSION当前版本号
	private String category;		//CATEGORY类别	04：刷卡器	08：POS机
	private String createTime;		//CREATE_TIME创建时间
	private String activeTime;		//ACTIVE_TIME激活时间
	private String lastUpdateTime;	//LAST_UPDATE_TIME最近一次更新时间
	private String terminalInfo;	//TERMINAL_INFO终端信息
	private String loginName;		//LOGIN_NAME登录名
	private String loginPwd;		//LOGIN_PWD登录密码
	private String lastLoginTime;	//LAST_LOGIN_TIME最近一次登录时间
	private String lastLoginInfo;	//LAST_LOGIN_INFO最近一次登录信息
	private String status;			//STATUS状态	0未使用	1正在使用	2暂停使用	3黑名单
	private String reserved;		//RESERVED扩展
	private String agentIdL1;			//一级代理商号
	private String agentIdL2;			//二级代理商号
	private String agentIdL3;		//3级代理商号
	private String agentIdL4;		//4级代理商号
	private String agentIdL5;		//4级代理商号
	private String agentIdL6;		//4级代理商号
	private String agentIdL7;		//4级代理商号
	private String agentIdL8;		//4级代理商号
	private String agentIdL9;		//4级代理商号
	private String agentIdL10;		//4级代理商号
						//存放最接近商户的代理商号
	private String level;
	private String countTsn;//统计终端数
	private String countValid;//统计终端开通数
////////////////////////////////GET   SET/////////////////////////////////////////////////////
	
	//终端列表查询  yjh辅助字段
	private String terminalId;
	
	public String getAgentIdL5() {
        return agentIdL5;
    }
    public void setAgentIdL5(String agentIdL5) {
        this.agentIdL5 = agentIdL5;
    }
    public String getAgentIdL6() {
        return agentIdL6;
    }
    public void setAgentIdL6(String agentIdL6) {
        this.agentIdL6 = agentIdL6;
    }
    public String getAgentIdL7() {
        return agentIdL7;
    }
    public void setAgentIdL7(String agentIdL7) {
        this.agentIdL7 = agentIdL7;
    }
    public String getAgentIdL8() {
        return agentIdL8;
    }
    public void setAgentIdL8(String agentIdL8) {
        this.agentIdL8 = agentIdL8;
    }
    public String getAgentIdL9() {
        return agentIdL9;
    }
    public void setAgentIdL9(String agentIdL9) {
        this.agentIdL9 = agentIdL9;
    }
    public String getAgentIdL10() {
        return agentIdL10;
    }
    public void setAgentIdL10(String agentIdL10) {
        this.agentIdL10 = agentIdL10;
    }
    public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public int getId() {
		return id;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getAgentIdL1() {
		return agentIdL1;
	}
	public void setAgentIdL1(String agentIdL1) {
		this.agentIdL1 = agentIdL1;
	}
	public String getAgentIdL2() {
		return agentIdL2;
	}
	public void setAgentIdL2(String agentIdL2) {
		this.agentIdL2 = agentIdL2;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTsn() {
		return tsn;
	}
	public void setTsn(String tsn) {
		this.tsn = tsn;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
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
	public String getPlatMerId() {
		return platMerId;
	}
	public void setPlatMerId(String platMerId) {
		this.platMerId = platMerId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getActiveTime() {
		return activeTime;
	}
	public void setActiveTime(String activeTime) {
		this.activeTime = activeTime;
	}
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public String getTerminalInfo() {
		return terminalInfo;
	}
	public void setTerminalInfo(String terminalInfo) {
		this.terminalInfo = terminalInfo;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLastLoginInfo() {
		return lastLoginInfo;
	}
	public void setLastLoginInfo(String lastLoginInfo) {
		this.lastLoginInfo = lastLoginInfo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	public String getCountTsn() {
		return countTsn;
	}
	public void setCountTsn(String countTsn) {
		this.countTsn = countTsn;
	}
	public String getCountValid() {
		return countValid;
	}
	public void setCountValid(String countValid) {
		this.countValid = countValid;
	}
	public String getAgentIdL3() {
		return agentIdL3;
	}
	public void setAgentIdL3(String agentIdL3) {
		this.agentIdL3 = agentIdL3;
	}
	public String getAgentIdL4() {
		return agentIdL4;
	}
	public void setAgentIdL4(String agentIdL4) {
		this.agentIdL4 = agentIdL4;
	}
	
}
