package com.bypay.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bypay.common.BaseAction;
import com.bypay.dao.SysModelDao;
import com.bypay.domain.SysModel;
import com.bypay.util.PageUtil;

public class SysModelAction extends BaseAction{
	@Inject
	private SysModelDao sysModelDao;
	private SysModel sysModel;
	private int page = 1;
	private int pageSize = 15;
	
	/**
	 * 系统模块列表
	 */
	public void getSysModelList() {
		int count = sysModelDao.getSysModelCount();
		Map map = PageUtil.getPageMap(page, pageSize);
		List<SysModel> models = sysModelDao.getSysModelList(map);
		JSONArray array = JSONArray.fromObject(models);
		JSONObject object = new JSONObject();
		object.put("Rows", array.toString());
		object.put("Total", count);
		String list = object.toString();
		System.out.println(list);
		try {
			getResponse().getWriter().write(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加系统模块
	 * @throws IOException 
	 */
	public void addSysModel() throws IOException{
		int result = 0;
		if (sysModel != null) {
			result = sysModelDao.addSysModel(sysModel);
		}
		if(result > 0) {
			getResponse().getWriter().write("1");
		} else {
			getResponse().getWriter().write("0");
		}
	}

	/**
	 * 删除系统模块
	 * @throws IOException 
	 */
	public void delSysModel() throws IOException {
		int result = 0;
		if (sysModel != null && !"".equals(sysModel.getId()) && null != sysModel.getId()) {
			result = sysModelDao.delSysModel(sysModel);
		}
		if(result > 0) {
			getResponse().getWriter().write("succ");
		} else {
			getResponse().getWriter().write("err");
		}
	}
	/**
	 * 根据ID获取系统模块
	 * @return
	 */
	public String getSysModelById() {
		String id = getRequest().getParameter("id");
		if(null != id && !"".equals(id)) {
			SysModel model = new SysModel();
			model.setId(Integer.valueOf(id));
			sysModel = sysModelDao.getSysModelById(model);
		}
		return "editSysModel";
	}
	/**
	 * 修改系统模块
	 * @throws IOException 
	 */
	public void editSysModel() throws IOException {
		int result = 0;
		if (sysModel != null) {
			result = sysModelDao.editSysModel(sysModel);
		}
		if(result > 0) {
			getResponse().getWriter().write("1");
		} else {
			getResponse().getWriter().write("0");
		}
	}
	public SysModel getSysModel() {
		return sysModel;
	}

	public void setSysModel(SysModel sysModel) {
		this.sysModel = sysModel;
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
	
}
