package com.bypay.domain;

public class RulesMerAmt {
	private String id;	//	ID	BIGINT		20	否	自增长
	private String merSysId;	//	MER_SYS_ID	BIGINT		20	否	机构号
	private String startAmt;	//	START_AMT	VARCHAR	20	是	起始金额
	private String endAmt;	//	END_AMT	VARCHAR	200	是	结束金额
	private String tractId;	//	TRACT_ID	VARCHAR	200	是	使用通道
	private String reserved1;	//	RESERVED1	varchar	100	是	保留域1
	private String reserved2;	//	RESERVED2	varchar	200	是	保留域2
	private String reserved3;	//	RESERVED3	varchar	300	是	保留域3
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
	public String getStartAmt() {
		return startAmt;
	}
	public void setStartAmt(String startAmt) {
		this.startAmt = startAmt;
	}
	public String getEndAmt() {
		return endAmt;
	}
	public void setEndAmt(String endAmt) {
		this.endAmt = endAmt;
	}
	public String getTractId() {
		return tractId;
	}
	public void setTractId(String tractId) {
		this.tractId = tractId;
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
