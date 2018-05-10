package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.SysManagerDao;
import com.bypay.domain.SysManager;

@Repository("sysManagerDao")
public  class SysManagerDaoImpl implements SysManagerDao {

	@Inject
	private SqlSessionTemplate sqlSessionTemplate;

	
	/**
	 * 
	 * 查询管理员
	 */
	public SysManager selectSysManager(SysManager sysManager) {
		return (SysManager) sqlSessionTemplate.selectOne("selectSysManager",
				sysManager);
	}

	/**
	 * 
	 * 添加管理员
	 */
	public int insertSysManager(SysManager sysManager) {
		return sqlSessionTemplate.insert("insertSysManager", sysManager);
	}

	
	/**
	 * 
	 * 查询管理员权限合法性
	 */
	public SysManager selectSysMerModel(SysManager sysManager) {
		return (SysManager) sqlSessionTemplate.selectOne("selectSysMerModel",
				sysManager);
	}

	/**
	 * 获取管理员总数
	 */
	@Override
	public int selectSysManagerCount(SysManager sysManager) {
		return (Integer) sqlSessionTemplate.selectOne("selectSysManagerCount", sysManager);
	}

	/**
	 * 查询所有管理员
	 */
	@Override
	public List<SysManager> selectSysManagerList(Map map) {
		return (List<SysManager>) sqlSessionTemplate.selectList("selectSysManagerList", map);
	}

	/**
	 * 更新管理员状态
	 */
	@Override
	public int updateSysManagerStatus(SysManager sysManager) {
		return sqlSessionTemplate.update("updateSysManagerStatus", sysManager);
	}

	/**
	 * 重置管理员密码
	 */
	@Override
	public int updateSmPass(Map mapper) {
		return sqlSessionTemplate.update("updateSmPass", mapper);
	}

	/**
	 * 得到本管理员的权限
	 */
	@Override
	public SysManager selectSysManagerBySmId(String id) {
		return (SysManager) sqlSessionTemplate.selectOne("selectSysManagerBySmId", id);
	}

	/**
	 * 更新管理员信息
	 */
	@Override
	public int updateSysManager(SysManager sysManager) {
		return sqlSessionTemplate.update("updateSysManager", sysManager);
	}

	/**
	 * 修改登录用户信息
	 */
	@Override
	public boolean updateLoginSysManager(SysManager sysManager) {
		if(sqlSessionTemplate.update("updateLoginSysManager", sysManager)>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void updatePassWord(SysManager manager) {
		sqlSessionTemplate.update("updatePassWord", manager);
	}

	/**
	 * 获取管理员信息
	 */
	@Override
	public SysManager getSysManager(SysManager manager) {
		return (SysManager) sqlSessionTemplate.selectOne("getSysManager", manager);
	}

	//查询系统管理员表 修改授权密码用看看 原始密码是否正确
	public SysManager selectLevelPass(SysManager manager) {
		return (SysManager) sqlSessionTemplate.selectOne("selectLevelPass", manager);
	}

	//修改授权密码
	public int updateLevelPass(SysManager manager) {
		return sqlSessionTemplate.update("updateLevelPass", manager);
	}

	@Override
	public Integer valiDateLevelPwd(SysManager sysManager) {
		return (Integer) sqlSessionTemplate.selectOne("valiDateLevelPwd",sysManager);
	}

	@Override
	public int updateSysManagerDetail(SysManager manager) {
		return sqlSessionTemplate.update("updateSysManagerDetail", manager);
	}


}
