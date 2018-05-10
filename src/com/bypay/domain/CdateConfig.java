package com.bypay.domain;

public class CdateConfig {
	private String id;	//	ID	BIGINT		20	否	自增长
	private String cdate;	//	CDATE	BIGINT		20	否	日期
	private String weekdy;	//	WEEKDY	VARCHAR	20	是	星期
	private String isWork;	//	IS_WORK	VARCHAR	2	是	是否工作日	1是	0否
	private String isXend;	//	IS_XEND	VARCHAR	2	是	是否旬末
	private String isMend;	//	IS_MEND	varchar	2	是	是否月末
	private String isQend;	//	IS_QEND	varchar	2	是	是否季末
	private String isHend;	//	IS_HEND	varchar	2	是	是否半年
	private String isYend;	//	IS_YEND	varchar	2	是	是否年末
	private String reserved1;	//	RESERVED1	varchar	100	是	保留域1
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	public String getWeekdy() {
		return weekdy;
	}
	public void setWeekdy(String weekdy) {
		this.weekdy = weekdy;
	}
	public String getIsWork() {
		return isWork;
	}
	public void setIsWork(String isWork) {
		this.isWork = isWork;
	}
	public String getIsXend() {
		return isXend;
	}
	public void setIsXend(String isXend) {
		this.isXend = isXend;
	}
	public String getIsMend() {
		return isMend;
	}
	public void setIsMend(String isMend) {
		this.isMend = isMend;
	}
	public String getIsQend() {
		return isQend;
	}
	public void setIsQend(String isQend) {
		this.isQend = isQend;
	}
	public String getIsHend() {
		return isHend;
	}
	public void setIsHend(String isHend) {
		this.isHend = isHend;
	}
	public String getIsYend() {
		return isYend;
	}
	public void setIsYend(String isYend) {
		this.isYend = isYend;
	}
	public String getReserved1() {
		return reserved1;
	}
	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

}
