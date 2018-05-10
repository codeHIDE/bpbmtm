package com.bypay.dao;

import java.util.List;
import java.util.Map;

import com.bypay.domain.SysManager;

public interface SysManagerDao {
	
	Integer valiDateLevelPwd(SysManager sysManager);
	
	//查询SysManager表 登录用，   
	SysManager selectSysManager(SysManager sysManager);
	
	//添加系统管理员 
	int insertSysManager(SysManager sysManager);
	
	SysManager selectSysMerModel(SysManager sysManager);

	/**
	 * 获取所有管理员信息总数
	 * @param sysManager
	 * @return
	 */
	int selectSysManagerCount(SysManager sysManager);

	/**
	 * 获取所有管理员信息
	 * @param map
	 * @return
	 */
	List<SysManager> selectSysManagerList(Map map);

	/**
	 * 更新管理员状态
	 * @param sysManager
	 * @return
	 */
	int updateSysManagerStatus(SysManager sysManager);

	/**
	 * 重置管理员密码
	 * @param mapper
	 * @return
	 */
	int updateSmPass(Map mapper);

	/**
	 * 得到本管理员的权限
	 * @param id
	 * @return
	 */
	SysManager selectSysManagerBySmId(String id);

	/**
	 * 更新管理员信息
	 * @param sysManager
	 * @return
	 */
	int updateSysManager(SysManager sysManager);
	
	/**
	 * 修改登录用户信息
	 * @param sysManager
	 * @return
	 */
	boolean updateLoginSysManager(SysManager sysManager);

	/**
	 * 修改登录用户密码
	 * @param manager
	 */
	void updatePassWord(SysManager manager);

	/**
	 * 获取管理员信息
	 * @param manager
	 * @return
	 */
	SysManager getSysManager(SysManager manager);
	
	//查询系统管理员表 修改授权密码用看看 原始密码是否正确
	SysManager selectLevelPass(SysManager manager);
	
	//修改授权密码
	int updateLevelPass(SysManager manager);
	
	//修改管理员基本信息
	int updateSysManagerDetail(SysManager manager);
	
}
