package com.bypay.domain;

public class TerminalBatchConfig {
	private String batchId;//	TRACT_ID	生成批次号自增11位
	private String createTime;//	CREATE_TIME	生成时间
	private String createNum;//	CREATE_NUM	生成数量
	private String merSysId;//	MER_SYS_ID	机构号
	private String factoryId;//	FACTORY_ID	厂商编号
	private String status;//	STATUS	状态
			//	0 未操作		1 请求成功		2 请求失败		3 已导入
	private String fileName;//	FILE_NAME	生成文件名
	private String reserved1;//	RESERVED1	保留域1 终端类型
	private String reserved2;//	RESERVED2	保留域2
	private String reserved3;//	RESERVED3	保留域3
	
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateNum() {
		return createNum;
	}
	public void setCreateNum(String createNum) {
		this.createNum = createNum;
	}
	public String getMerSysId() {
		return merSysId;
	}
	public void setMerSysId(String merSysId) {
		this.merSysId = merSysId;
	}
	public String getFactoryId() {
		return factoryId;
	}
	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
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

}
