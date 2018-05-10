package com.bypay.domain;

public class MerInfo {

	private String merSysId; 			// MER_SYS_ID BIGINT 1 否 自增
	private String merName; 			// MER_NAME VARCHAR 60 否 工商注册名
	private String merRegNo; 			// MER_REG_NO VARCHAR 20 否 工商注册号
	private String merTaxNo; 			// MER_TAX_NO VARCHAR 20 否 税务登记号
	private String organizationCode; 	// ORGANIZATION_CODE VARCHAR 20 否 组织机构代码
	private String merRegAddr; 			// MER_REG_ADDR VARCHAR 200 是 工商注册地址
	private String merLegalPerson; 		// MER_LEGAL_PERSON VARCHAR 60 否 法人姓名
	private String merLegalIdcard; 		// MER_LEGAL_IDCARD VARCHAR 20 否 法人证件号
	private String regCapital; 			// REG_CAPITAL VARCHAR 10 是 注册资本
	private String merKind; 			// MER_KIND VARCHAR 30 是 商户性质
	private String contactor; 			// CONTACTOR VARCHAR 60 是 联系人
	private String contactorPhone; 		// CONTACTOR_PHONE VARCHAR 20 是 联系电话
	private String contactorAddr; 		// CONTACTOR_ADDR VARCHAR 200 是 联系地址
	private String contactorEmail; 		// CONTACTOR_EMAIL VARCHAR 100 是 联系邮箱
	private String settAccountName;		// SETT_ACCOUNT_NAME VARCHAR 60 否 结算账户名
	private String settAccountNo; 		// SETT_ACCOUNT_NO VARCHAR 30 否 结算账户号
	private String settAgency; 			// SETT_AGENCY VARCHAR 30 否 结算机构
	private String settAccType;			//SETT_ACC_TYPE	VARCHAR	2	否	结算账类型1 对公2 对私
	private String signDate; 			// SIGN_DATE VARCHAR 10 是 签约日期
	private String signPerson; 			// SIGN_PERSON VARCHAR 30 是 签约人
	private String dispName; 			// DISP_NAME VARCHAR 60 是 显示名称
	private String platMerId; 			// PLAT_MER_ID BIGINT 1 否 所属平台方(外键)
	private String createTime; 			// CREATE_TIME VARCHAR 20 否 创建时间
	private String status; 				// STATUS VARCHAR 1 否 状态 0：未审核 1：已审批 2：已上线3：暂停 4：黑名单
	private String authStatus; 			// AUTH_STATUS VARCHAR 1 实人认证状态 0：未认证 1：认证成功 // 2：认证失败
	private String logo; 				// LOGO VARCHAR 50 是 Logo图,用于管理平台显示
	private String color; 				// COLOR VARCHAR 10 是 色调,用于管理平台显示
	private String billCycle; 			// BILL_CYCLE VARCHAR 5 是 清分周期1|D 1|M
	private String remark; 				// REMARK VARCHAR 100 是 备注
	private String reserved; 			 // RESERVED VARCHAR 50 是 扩展
	private String accessType;//机构接入类别
	private String isIntoPieces;//是否暂停进件 0.否 1是
	
	private PlatMerInfo platMerInfo;
	private String tractId;
	private String backUrl;
	private String payTract;
	private String rate;
	private String lineNum; //联行号
	private String openBank; //开户行
    private String region; // 地区码 REGION
    private String merSysIds;
    
	public String getMerSysIds() {
        return merSysIds;
    }

    public void setMerSysIds(String merSysIds) {
        this.merSysIds = merSysIds;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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

    public String getIsIntoPieces() {
		return isIntoPieces;
	}

	public void setIsIntoPieces(String isIntoPieces) {
		this.isIntoPieces = isIntoPieces;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public String getPayTract() {
		return payTract;
	}

	public void setPayTract(String payTract) {
		this.payTract = payTract;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getTractId() {
		return tractId;
	}

	public void setTractId(String tractId) {
		this.tractId = tractId;
	}

	public PlatMerInfo getPlatMerInfo() {
		return platMerInfo;
	}

	public void setPlatMerInfo(PlatMerInfo platMerInfo) {
		this.platMerInfo = platMerInfo;
	}

	public String getMerSysId() {
		return merSysId;
	}

	public void setMerSysId(String merSysId) {
		this.merSysId = merSysId;
	}

	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

	public String getMerRegNo() {
		return merRegNo;
	}

	public void setMerRegNo(String merRegNo) {
		this.merRegNo = merRegNo;
	}

	public String getMerTaxNo() {
		return merTaxNo;
	}

	public void setMerTaxNo(String merTaxNo) {
		this.merTaxNo = merTaxNo;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getMerRegAddr() {
		return merRegAddr;
	}

	public void setMerRegAddr(String merRegAddr) {
		this.merRegAddr = merRegAddr;
	}

	public String getMerLegalPerson() {
		return merLegalPerson;
	}

	public void setMerLegalPerson(String merLegalPerson) {
		this.merLegalPerson = merLegalPerson;
	}

	public String getMerLegalIdcard() {
		return merLegalIdcard;
	}

	public void setMerLegalIdcard(String merLegalIdcard) {
		this.merLegalIdcard = merLegalIdcard;
	}

	public String getRegCapital() {
		return regCapital;
	}

	public String getSettAccType() {
		return settAccType;
	}

	public void setSettAccType(String settAccType) {
		this.settAccType = settAccType;
	}

	public void setRegCapital(String regCapital) {
		this.regCapital = regCapital;
	}

	public String getMerKind() {
		return merKind;
	}

	public void setMerKind(String merKind) {
		this.merKind = merKind;
	}

	public String getContactor() {
		return contactor;
	}

	public void setContactor(String contactor) {
		this.contactor = contactor;
	}

	public String getContactorPhone() {
		return contactorPhone;
	}

	public void setContactorPhone(String contactorPhone) {
		this.contactorPhone = contactorPhone;
	}

	public String getContactorAddr() {
		return contactorAddr;
	}

	public void setContactorAddr(String contactorAddr) {
		this.contactorAddr = contactorAddr;
	}

	public String getContactorEmail() {
		return contactorEmail;
	}

	public void setContactorEmail(String contactorEmail) {
		this.contactorEmail = contactorEmail;
	}

	public String getSettAccountName() {
		return settAccountName;
	}

	public void setSettAccountName(String settAccountName) {
		this.settAccountName = settAccountName;
	}

	public String getSettAccountNo() {
		return settAccountNo;
	}

	public void setSettAccountNo(String settAccountNo) {
		this.settAccountNo = settAccountNo;
	}

	public String getSettAgency() {
		return settAgency;
	}

	public void setSettAgency(String settAgency) {
		this.settAgency = settAgency;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getSignPerson() {
		return signPerson;
	}

	public void setSignPerson(String signPerson) {
		this.signPerson = signPerson;
	}

	public String getDispName() {
		return dispName;
	}

	public void setDispName(String dispName) {
		this.dispName = dispName;
	}

	public String getPlatMerId() {
		return platMerId;
	}

	public void setPlatMerId(String platMerId) {
		this.platMerId = platMerId;
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

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBillCycle() {
		return billCycle;
	}

	public void setBillCycle(String billCycle) {
		this.billCycle = billCycle;
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

}
