package com.bypay.dao;

import java.util.List;
import java.util.Map;

import com.bypay.domain.SysModel;

public interface SysModelDao {
	
	
	/**
	 * 查权限列表
	 * @return
	 */
	public List<SysModel> selectSysModel();
	
	public SysModel selectModelDetail(SysModel sysModel);

	/**
	 * 获取系统模块表总数
	 * @return
	 */
	public int getSysModelCount();

	/**
	 * 获取系统模块数据
	 * @param map
	 * @return
	 */
	public List<SysModel> getSysModelList(Map map);

	/**
	 * 添加系统模块
	 * @param sysModel
	 * @return
	 */
	public int addSysModel(SysModel sysModel);

	/**
	 * 删除系统模块
	 * @param sysModel
	 * @return
	 */
	public int delSysModel(SysModel sysModel);

	/**
	 * 根据ID获取系统模块
	 * @param sysModel
	 * @return
	 */
	public SysModel getSysModelById(SysModel sysModel);

	/**
	 * 修改系统模块
	 * @param sysModel
	 * @return
	 */
	public int editSysModel(SysModel sysModel);
}
