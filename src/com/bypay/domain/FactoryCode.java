package com.bypay.domain;

public class FactoryCode {
	
	
	private String id; 							//ID								BIGINT				8		否	自增长
	private String factoryNo;		 		//FACTORY_NO			VARCHAR	30	是	厂商编号
	private String factoryName; 		//FACTORY_NAME	VARCHAR	20	是	厂商名称
	private String reserved1; 			//RESERVED1			varchar	100	是	保留域1
	private String reserved2; 			//RESERVED2			varchar	200	是	保留域2
	private String reserved3; 			//RESERVED3			varchar	300	是	保留域3
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFactoryNo() {
		return factoryNo;
	}
	public void setFactoryNo(String factoryNo) {
		this.factoryNo = factoryNo;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
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
