package com.bypay.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bypay.dao.SysManagerDao;
import com.bypay.domain.SysManager;
import com.bypay.service.SysManagerService;


@Service("sysManagerService")
public class SysManagerServiceImpl implements SysManagerService{

	@Inject
	private SysManagerDao sysManagerDao;
	
	/**
	 * 查询系统管理员
	 * @param sysManager
	 * @return
	 */
	public SysManager selectSysManager(SysManager sysManager) {
		return sysManagerDao.selectSysManager(sysManager);
	}

	/**
	 * 添加系统管理员
	 * @param sysManager
	 * @return
	 */
	public int insertSysManager(SysManager sysManager) {
		int i = -1;
		try{
			i =sysManagerDao.insertSysManager(sysManager);
		}catch(Exception e){
			e.printStackTrace();
			i=-1;
		}
		return i;
	}
	
	/**
	 * 查询系统管理员权限合法性
	 * @param sysManager
	 * @return
	 */
	public SysManager selectSysMerModel(SysManager sysManager) {
		return sysManagerDao.selectSysMerModel(sysManager);
	}

	/**
	 * 获取所有管理员信息总数
	 */
	@Override
	public int selectSysManagerCount(SysManager sysManager) {
		return sysManagerDao.selectSysManagerCount(sysManager);
	}

	/**
	 * 获取所有管理员信息
	 */
	@Override
	public List<SysManager> selectSysManagerList(Map map) {
		List<SysManager> list = null;
		try {
			list = sysManagerDao.selectSysManagerList(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 更新管理员状态
	 */
	@Override
	public int updateSysManagerStatus(SysManager sysManager) {
		return sysManagerDao.updateSysManagerStatus(sysManager);
	}

	/**
	 * 重置管理员密码
	 */
	@Override
	public int updateSmPass(String loginPwd, String id) {
		Map mapper = new HashMap();
		mapper.put("loginPwd", loginPwd);
		mapper.put("id", id);
		return sysManagerDao.updateSmPass(mapper);
	}

	/**
	 * 得到本管理员的权限
	 */
	@Override
	public SysManager selectSysManagerBySmId(String id) {
		return sysManagerDao.selectSysManagerBySmId(id);
	}

	@Override
	public int updateSysManager(SysManager sysManager) {
		return sysManagerDao.updateSysManager(sysManager);
	}

	/**
	 * 修改登录用户信息
	 */
	@Override
	public boolean updateLoginSysManager(SysManager sysManager) {
		return sysManagerDao.updateLoginSysManager(sysManager);
	}

	/**
	 * 修改登录用户密码
	 */
	@Override
	public void updatePassWord(SysManager manager) {
		sysManagerDao.updatePassWord(manager);
	}

	/**
	 * 获取管理员信息
	 */
	@Override
	public SysManager getSysManager(SysManager manager) {
		return sysManagerDao.getSysManager(manager);
	}

	//查询系统管理员表 修改授权密码用看看 原始密码是否正确
	@Override
	public SysManager selectLevelPass(SysManager manager) {
		return sysManagerDao.selectLevelPass(manager);
	}

	//修改授权密码
	@Override
	public int updateLevelPass(SysManager manager) {
		return sysManagerDao.updateLevelPass(manager);
	}

	@Override
	public SysManager selectSysManagerLevelPwd(SysManager manager) {
		return sysManagerDao.selectSysManager(manager);
	}

	@Override
	public Integer valiDateLevelPwd(SysManager sysManager) {
		return sysManagerDao.valiDateLevelPwd(sysManager);
	}

	@Override
	public int updateSysManagerDetail(SysManager manager) {
		return sysManagerDao.updateSysManagerDetail(manager);
	}

}
