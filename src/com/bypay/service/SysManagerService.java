package com.bypay.service;

import java.util.List;
import java.util.Map;

import com.bypay.domain.SysManager;

public interface SysManagerService {
	
	Integer valiDateLevelPwd(SysManager sysManager);
	
	public SysManager selectSysManager(SysManager sysManager);
	
	public int insertSysManager(SysManager sysManager);
	  
	public SysManager selectSysMerModel(SysManager sysManager);

	/**
	 * 查询所有管理员信息总条数
	 * @param sysManager
	 * @return
	 */
	public int selectSysManagerCount(SysManager sysManager);

	/**
	 * 获取所有管理员信息
	 * @param map
	 * @return
	 */
	public List<SysManager> selectSysManagerList(Map map);

	/**
	 * 更新管理员状态(正在使用) 改为暂停
	 * @param sysManager
	 * @return
	 */
	public int updateSysManagerStatus(SysManager sysManager);

	/**
	 * 重置管理员密码
	 * @param hashString
	 * @param id
	 * @return
	 */
	public int updateSmPass(String hashString, String id);

	/**
	 * 得到本管理员的权限
	 * @param id
	 * @return
	 */
	public SysManager selectSysManagerBySmId(String id);

	/**
	 * 更新管理员信息
	 * @param sysManager
	 * @return
	 */
	public int updateSysManager(SysManager sysManager);

	/**
	 * 修改登录成功后的用户信息
	 * @param sysManager
	 * @return
	 */
	public boolean updateLoginSysManager(SysManager sysManager);

	/**
	 * 修改登录用户密码
	 * @param manager
	 */
	public void updatePassWord(SysManager manager);
	
	//查询系统管理员表 修改授权密码用看看 原始密码是否正确
	SysManager selectLevelPass(SysManager manager);
	
	//修改授权密码
	int updateLevelPass(SysManager manager);
	
	SysManager selectSysManagerLevelPwd(SysManager manager);

	/**
	 * 获取管理员信息
	 * @param manager
	 * @return
	 */
	public SysManager getSysManager(SysManager manager);	
	
	//修改管理员基本信息
	int updateSysManagerDetail(SysManager manager);

}
