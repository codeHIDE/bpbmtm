package com.bypay.domain;

import com.bypay.util.BaseExcel;

/**
 * 异常交易
 * @author bypay
 *
 */
public class OrderRisk {
	
	private String orderId;
	private String submerId;
	private String orderRiskType; //异常交易类型
	private String orderRiskProcType; //异常处理方式
	private String orderRiskProcTime; //处理时间
	private String reseRved1; //保留域
	private String reseRved2;  //保留域
	private String reseRved3; //保留域
	private String ext;  //扩展域
	
	private int page;
	private int pageSize;
	private String isDownload;//判断是否下载 1:下载 
	
	//---------------------将数字代表的具体含义实体化-------
	private String orderRiskTypes;//异常交易类型
	private String orderRiskProcTypes;//异常处理方式
	
	public String getOrderRiskTypes() {
		if("00".equals(orderRiskType)){
			orderRiskTypes = "手动标记";
		}else if("11".equals(orderRiskType)){
			orderRiskTypes = "用户多点操作";
		}else if("12".equals(orderRiskType)){
			orderRiskTypes = "卡号对应多个用户身份";
		}else if("13".equals(orderRiskType)){
			orderRiskTypes = "相同身份对应多卡号";
		}else if("21".equals(orderRiskType)){
			orderRiskTypes = "单订单多次支付失败";
		}else if("22".equals(orderRiskType)){
			orderRiskTypes = "单日交易总额大于平均值20%";
		}else if("23".equals(orderRiskType)){
			orderRiskTypes = "单卡多点同时交易";
		}else if("24".equals(orderRiskType)){
			orderRiskTypes = "零点至五点的交易";
		}else{
			orderRiskTypes = "未知";
		}
		return orderRiskTypes;
	}
	public void setOrderRiskTypes(String orderRiskTypes) {
		this.orderRiskTypes = orderRiskTypes;
	}
	public String getOrderRiskProcTypes() {
		if("0".equals(orderRiskProcType)) {
			orderRiskProcTypes = "继续交易";
		}
		if("1".equals(orderRiskProcType)) {
			orderRiskProcTypes = "报警（默认）";
		}
		if("2".equals(orderRiskProcType)) {
			orderRiskProcTypes = "拒绝";
		}
		return orderRiskProcTypes;
	}
	public void setOrderRiskProcTypes(String orderRiskProcTypes) {
		this.orderRiskProcTypes = orderRiskProcTypes;
	}
	public String getIsDownload() {
		return isDownload;
	}
	public void setIsDownload(String isDownload) {
		this.isDownload = isDownload;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getSubmerId() {
		return submerId;
	}
	public void setSubmerId(String submerId) {
		this.submerId = submerId;
	}
	public String getOrderRiskType() {
		return orderRiskType;
	}
	public void setOrderRiskType(String orderRiskType) {
		this.orderRiskType = orderRiskType;
	}
	public String getOrderRiskProcType() {
		return orderRiskProcType;
	}
	public void setOrderRiskProcType(String orderRiskProcType) {
		this.orderRiskProcType = orderRiskProcType;
	}
	public String getOrderRiskProcTime() {
		return orderRiskProcTime;
	}
	public void setOrderRiskProcTime(String orderRiskProcTime) {
		this.orderRiskProcTime = orderRiskProcTime;
	}
	public String getReseRved1() {
		return reseRved1;
	}
	public void setReseRved1(String reseRved1) {
		this.reseRved1 = reseRved1;
	}
	public String getReseRved2() {
		return reseRved2;
	}
	public void setReseRved2(String reseRved2) {
		this.reseRved2 = reseRved2;
	}
	public String getReseRved3() {
		return reseRved3;
	}
	public void setReseRved3(String reseRved3) {
		this.reseRved3 = reseRved3;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	
	public String getCellValue(int i) {
		switch (i) {
		case 0:
			return String.valueOf(orderId);
		case 1:
			return String.valueOf(submerId);
		case 2:
			if ("00".equals(orderRiskType)) {
				return "手动标记";
			}
			if ("11".equals(orderRiskType)) {
				return "用户多点操作";
			}
			if ("12".equals(orderRiskType)) {
				return "卡号对应多个用户身份";
			}
			if ("13".equals(orderRiskType)) {
				return "相同身份对应多卡号";
			}
			if ("21".equals(orderRiskType)) {
				return "单订单多次支付失败";
			}
			if ("22".equals(orderRiskType)) {
				return "单日交易总额大于平均值20%";
			}
			if ("23".equals(orderRiskType)) {
				return "单卡多点同时交易";
			}
			if ("24".equals(orderRiskType)) {
				return "零点至五点的交易";
			}
		case 3:
			if ("0".equals(orderRiskProcType)) {
				return "继续交易";
			}
			if ("1".equals(orderRiskProcType)) {
				return "报警（默认）";
			}
			if ("2".equals(orderRiskProcType)) {
				return "拒绝";
			}
		case 4:
			return String.valueOf(orderRiskProcTime);
		default:
			return "";
		}
	}
	

} 
