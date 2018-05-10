package com.bypay.domain;

public class  TerminalType {

	private String productId; 	 		//PRODUCT_ID				VARCHAR	3	否		
	private String productNmae; 	 //PRODUCT_NAME		VARCHAR	60	是		
	private String productDesc;  	//PRODUCT_DESC			VARCHAR	150	是		
	private String status;  				//STATUS						SMALLINT	2	是		
	private String remark;  				//REMARK						VARCHAR	90	是		
	private String type;  					//TYPE							SMALLINT	2	是	
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductNmae() {
		return productNmae;
	}
	public void setProductNmae(String productNmae) {
		this.productNmae = productNmae;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
