package com.bypay.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bypay.common.BaseAction;
import com.bypay.dao.WaresInfoDao;
import com.bypay.dao.WaresSpecInfoDao;
import com.bypay.domain.WaresInfo;
import com.bypay.domain.WaresSpecInfo;
import com.bypay.util.PageUtil;

public class WaresAction extends BaseAction {
	/** *分页开始** */
	private int page = 1;
	private int pageSize = 15;
	private WaresInfo waresInfo;
	private List<WaresInfo> waresInfoList; // 用于存储查询所有的子商务信息集合
	private WaresSpecInfo waresSpecInfo;
	private List<WaresSpecInfo> waresSpecInfoList; // 用于存储查询所有的子商务信息集合
	
	@Inject
	private WaresInfoDao waresInfoDao; 
	@Inject
	private WaresSpecInfoDao waresSpecInfoDao; 
	
	public void selectWares(){
		try {
			if (waresInfo == null) {
				waresInfo = new WaresInfo();
			}
			Map map = PageUtil.getPageMap(page, pageSize);
			map.put("waresInfo", waresInfo);
			int count = waresInfoDao.selectWaresCount(map);
			waresInfoList = waresInfoDao.selectWares(map);
			JSONArray array = JSONArray.fromObject(waresInfoList);
			JSONObject object = new JSONObject();
			object.put("Rows", array.toString());
			object.put("Total", count);
			getResponse().getWriter().write(object.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addWares(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		waresInfo.setCreateTime(sdf.format(new Date()));
		waresInfoDao.insertWares(waresInfo);
		try {
			getResponse().getWriter().write("添加成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectSpec(){
		try {
			if (waresSpecInfo == null) {
				waresSpecInfo = new WaresSpecInfo();
			}
			Map map = PageUtil.getPageMap(page, pageSize);
			map.put("waresSpecInfo", waresSpecInfo);
			int count = waresSpecInfoDao.selectSpecCount(map);
			waresSpecInfoList = waresSpecInfoDao.selectSpec(map);
			JSONArray array = JSONArray.fromObject(waresSpecInfoList);
			JSONObject object = new JSONObject();
			object.put("Rows", array.toString());
			object.put("Total", count);
			getResponse().getWriter().write(object.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String addspecPage(){
		
		return "addspecPage";
	}
	
	public void addSpec(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		waresSpecInfo.setCreateTime(sdf.format(new Date()));
		waresSpecInfoDao.insertSpec(waresSpecInfo);
		try {
			getResponse().getWriter().write("添加成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public WaresSpecInfoDao getWaresSpecInfoDao() {
		return waresSpecInfoDao;
	}

	public void setWaresSpecInfoDao(WaresSpecInfoDao waresSpecInfoDao) {
		this.waresSpecInfoDao = waresSpecInfoDao;
	}

	public WaresSpecInfo getWaresSpecInfo() {
		return waresSpecInfo;
	}

	public void setWaresSpecInfo(WaresSpecInfo waresSpecInfo) {
		this.waresSpecInfo = waresSpecInfo;
	}

	public List<WaresSpecInfo> getWaresSpecInfoList() {
		return waresSpecInfoList;
	}

	public void setWaresSpecInfoList(List<WaresSpecInfo> waresSpecInfoList) {
		this.waresSpecInfoList = waresSpecInfoList;
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

	public WaresInfo getWaresInfo() {
		return waresInfo;
	}

	public void setWaresInfo(WaresInfo waresInfo) {
		this.waresInfo = waresInfo;
	}

	public List<WaresInfo> getWaresInfoList() {
		return waresInfoList;
	}

	public void setWaresInfoList(List<WaresInfo> waresInfoList) {
		this.waresInfoList = waresInfoList;
	}

	public WaresInfoDao getWaresInfoDao() {
		return waresInfoDao;
	}

	public void setWaresInfoDao(WaresInfoDao waresInfoDao) {
		this.waresInfoDao = waresInfoDao;
	}

	
	
}
