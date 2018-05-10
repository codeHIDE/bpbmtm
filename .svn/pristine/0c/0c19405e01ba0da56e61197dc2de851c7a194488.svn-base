package com.bypay.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bypay.common.BaseAction;
import com.bypay.dao.AgenctModelDao;
import com.bypay.domain.AgenctModel;
import com.bypay.util.PageUtil;

@SuppressWarnings("serial")
public class AgencyModelAction extends BaseAction{
	@Inject
	private AgenctModelDao agenctModelDao;					//代理商模块Dao层队形
	private List<AgenctModel> agenctModelList;				//用于存储查询到的代理商模块
	private AgenctModel agenctModel;						//代理商模块实体对象
	private String id;										//标记
	
	// 分页开始	
	private int page = 1;
	private int pageSize = 15;
	
	//代理商模块列表
	@SuppressWarnings("unchecked")
	public void agencyModel(){
		try {
			int num=agenctModelDao.selectAgenctModelCount();
			Map map=PageUtil.getPageMap(page, pageSize);
			agenctModelList=agenctModelDao.selectAgenctModelAll(map);
			JSONArray array = JSONArray.fromObject(agenctModelList);
			JSONObject object = new JSONObject();
			object.put("Rows", array.toString());
			object.put("Total", num);
			String s=object.toString();
			getResponse().getWriter().write(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//代理商模块添加跳转
	public String insertAgencyModel(){
		return "insert";
	}
	//代理商模块添加
	public void addAgencyModel(){
		try {
			boolean b=agenctModelDao.insertAgenctModel(agenctModel);
			if(b)
				getResponse().getWriter().write("1");
			else
				getResponse().getWriter().write("2");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//代理商模块删除
	public void delectAgencyModel(){
		try {
			if(agenctModel==null){
				agenctModel=new AgenctModel();
			}
			agenctModel.setId(id);
			boolean b=agenctModelDao.deleteAgenctModel(agenctModel);
			if(b)
				getResponse().getWriter().write("succ");
			else
				getResponse().getWriter().write("finl");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//代理商模块修改跳转
	public String updAgencyModel(){
		try {
			if(agenctModel==null){
				agenctModel=new AgenctModel();
			}
			agenctModel.setId(id);
			agenctModel=agenctModelDao.selectAgenctModel(agenctModel);
			if(agenctModel!=null)
				getResponse().getWriter().write("dyes");
			else
				getResponse().getWriter().write("dno");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "update";
	}
	//代理商模块修改
	public void updateAgencyModel(){
		try {
			boolean b=agenctModelDao.updateAgenctModel(agenctModel);
			if(b)
				getResponse().getWriter().write("1");
			else
				getResponse().getWriter().write("2");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AgenctModelDao getAgenctModelDao() {
		return agenctModelDao;
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
	public void setAgenctModelDao(AgenctModelDao agenctModelDao) {
		this.agenctModelDao = agenctModelDao;
	}

	public List<AgenctModel> getAgenctModelList() {
		return agenctModelList;
	}

	public void setAgenctModelList(List<AgenctModel> agenctModelList) {
		this.agenctModelList = agenctModelList;
	}
	public AgenctModel getAgenctModel() {
		return agenctModel;
	}
	public void setAgenctModel(AgenctModel agenctModel) {
		this.agenctModel = agenctModel;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
