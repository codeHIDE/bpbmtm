package com.bypay.dao;

import java.util.List;
import java.util.Map;

import com.bypay.domain.MobileUser;

public interface MobileUserDao {
	
	public int insertMobileUser(MobileUser mobileUser);
	
	public int deleteMobileUser(MobileUser mobileUser);
	
	public MobileUser getMobileUser(MobileUser mobileUser);
	
	public MobileUser getMobileUserById(MobileUser mobileUser);
	
	public void updateUserEavFail(MobileUser mobileUser);
	
	/**
	   * 修改设备最近登录时间
	   * 
	   * @param subMerTerminal
	   */
	public void updateUserByLastLoginTime(MobileUser mobileUser);
	
	
	public void updateMobileUser(MobileUser mobileUser);
	
	public boolean updateTerminal(MobileUser mobileUser);
	
	  /**
	   * 查询终端信息
	   */
	  List<MobileUser> selectMobileUsers(Map map);

	  Integer selectMobileUsersCount(Map map);
	  
	 public boolean updateMemberId(MobileUser mobileUser);

	
}
