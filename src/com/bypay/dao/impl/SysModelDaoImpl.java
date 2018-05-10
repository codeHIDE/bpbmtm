package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.SysModelDao;
import com.bypay.domain.SysModel;

@Repository("sysModelDao")
public class SysModelDaoImpl implements SysModelDao {

	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 
	 * 查询权限列表
	 */
	public List<SysModel> selectSysModel() {
		return sqlSessionTemplate.selectList("selectSysModel",null);
	}

	/**
	 * 
	 * 查询权限列表
	 */
	public SysModel selectModelDetail(SysModel sysModel) {
		return (SysModel) sqlSessionTemplate.selectOne("selectModelDetail",sysModel);
	}

	/**
	 * 获取系统模块列表总数
	 */
	@Override
	public int getSysModelCount() {
		return (Integer) sqlSessionTemplate.selectOne("getSysModelCount",null);
	}

	/**
	 * 获取系统模块数据
	 */
	@Override
	public List<SysModel> getSysModelList(Map map) {
		return sqlSessionTemplate.selectList("getSysModelList",map);
	}

	/**
	 * 添加系统模块
	 */
	@Override
	public int addSysModel(SysModel sysModel) {
		return sqlSessionTemplate.insert("addSysModel", sysModel);
	}

	/**
	 * 删除系统模块
	 */
	@Override
	public int delSysModel(SysModel sysModel) {
		return sqlSessionTemplate.delete("delSysModel", sysModel);
	}

	/**
	 * 根据ID获取系统模块
	 */
	@Override
	public SysModel getSysModelById(SysModel sysModel) {
		return (SysModel) sqlSessionTemplate.selectOne("getSysModelById", sysModel);
	}

	/**
	 * 修改系统模块
	 */
	@Override
	public int editSysModel(SysModel sysModel) {
		return sqlSessionTemplate.update("editSysModel", sysModel);
	}

}
