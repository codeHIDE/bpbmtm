package com.bypay.domain;

public class MerTerminalInfo {
	private String id;						//ID    BIGINT 否  主键
	private String merSysId;						//MER_SYS_ID		BIGINT		1	否	外键
	private String category;						//CATEGORY		VARCHAR	50	是	设备类别04：刷卡器08：POS机
	private String terminalCode;				//TERMINAL_CODE		VARCHAR	10	否	设备代码
	private String terminalSysterm;			//TERMINAL_SYSTERM	VARCHAR	10	   否	设备系统信息IOS/ANDROID/WIN
	private String fileName;						//FILE_NAME		VARCHAR	50	否	文件名
	private String version;							//VERSION			VARCHAR	10	否	版本号
	private String versionDesc;					//VERSION_DESC		VARCHAR	200	是	版本信息描述
	private String updateType;					//UPDATE_TYPE		VARCHAR	2	否	更新类型00不更新01强制更新02非强制更新
	private String updatePath;					//UPDATE_PATH		VARCHAR	100	是	更新地址
	private String createTime;					//CREATE_TIME		VARCHAR	20	否	版本创建时间
	private String status;							//STATUS			VARCHAR	1	否	状态0不可用1可用
	private String reserved;						//RESERVED		VARCHAR	50	是	扩展
	
	private String fileType;				//文件类型
	
	
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMerSysId() {
		return merSysId;
	}
	public void setMerSysId(String merSysId) {
		this.merSysId = merSysId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTerminalCode() {
		return terminalCode;
	}
	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}
	public String getTerminalSysterm() {
		return terminalSysterm;
	}
	public void setTerminalSysterm(String terminalSysterm) {
		this.terminalSysterm = terminalSysterm;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getVersionDesc() {
		return versionDesc;
	}
	public void setVersionDesc(String versionDesc) {
		this.versionDesc = versionDesc;
	}
	public String getUpdateType() {
		return updateType;
	}
	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}
	public String getUpdatePath() {
		return updatePath;
	}
	public void setUpdatePath(String updatePath) {
		this.updatePath = updatePath;
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
	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

}
