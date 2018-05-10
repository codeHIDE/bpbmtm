package com.bypay.dao;

import java.util.List;
import java.util.Map;

import com.bypay.domain.AccountManage;

public interface AccountManageDao {
	
	//查询所有清分款上账表的信息 
	List<AccountManage> selectAccountManageList(Map map);
	
	//查询条数 分页用
	int selectAccountManageCount(AccountManage accountManage);
	
	//详情
	AccountManage selectAccountManage(AccountManage accountManage);
	
	//更新修改 金额
	int updateAccountManage(AccountManage accountManage);

	/**
	 * 修改预存款状态
	 * @param manage
	 */
	void updateAccountManageByStatus(AccountManage manage);
	

}
