package com.bypay.action;

import java.io.IOException;
import java.util.*;

import javax.inject.Inject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bypay.common.BaseAction;
import com.bypay.dao.PayBankCodeDao;
import com.bypay.domain.PayBankCode;
import com.bypay.util.PageUtil;

public class PayBankCodeAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Inject
	private PayBankCodeDao payBankCodeDao;
	private PayBankCode payBankCode;
	private List<PayBankCode> payBankCodeList;
	private String id;
	//分页
	private String page="1";
	private String pageSize="15";
	
	//查询所有
	@SuppressWarnings("unchecked")
	public void selectpayBankCodeAll(){
		try {
			int p=Integer.parseInt(page);
			int ps=Integer.parseInt(pageSize);
			Map map = PageUtil.getPageMap(p,ps);
			ps=payBankCodeDao.selectPayBankCodeCount();
			payBankCodeList=payBankCodeDao.selectPayBankCodeAll(map);
			JSONArray array = JSONArray.fromObject(payBankCodeList);
			JSONObject object = new JSONObject();
			object.put("Rows", array.toString());
			object.put("Total", ps);
			getResponse().getWriter().write(object.toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//点击增加  跳转
	public String insertTop(){
		return "goToInsertFactoryCode";
	}
	
	//增加
	public void insertpayBankCode(){
		try {
			if(payBankCode!=null){
				boolean b=payBankCodeDao.insertPayBankCode(payBankCode);
				if(b)
					getResponse().getWriter().write("true");
				else
					getResponse().getWriter().write("false");
			}else{
				getResponse().getWriter().write("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//删除
	public void deletepayBankCode(){
		try {
			if(id!=null){
				payBankCode=new PayBankCode();
				payBankCode.setId(id);
				if(payBankCodeDao.deletePayBankCode(payBankCode))
					getResponse().getWriter().write("true");
				else
					getResponse().getWriter().write("false");
			}else{
				getResponse().getWriter().write("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public PayBankCodeDao getPayBankCodeDao() {
		return payBankCodeDao;
	}
	public void setPayBankCodeDao(PayBankCodeDao payBankCodeDao) {
		this.payBankCodeDao = payBankCodeDao;
	}
	public PayBankCode getPayBankCode() {
		return payBankCode;
	}
	public void setPayBankCode(PayBankCode payBankCode) {
		this.payBankCode = payBankCode;
	}
	public List<PayBankCode> getPayBankCodeList() {
		return payBankCodeList;
	}
	public void setPayBankCodeList(List<PayBankCode> payBankCodeList) {
		this.payBankCodeList = payBankCodeList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
