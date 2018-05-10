package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.AccountManageDao;
import com.bypay.domain.AccountManage;

@Repository("accountManageDao")
public class AccountManageDaoImpl  implements AccountManageDao{
	
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;

	@Override //查询条数
	public int selectAccountManageCount(AccountManage accountManage) {
		return (Integer) sqlSessionTemplate.selectOne("selectAccountManageCount", accountManage);
	}

	@Override//查询 LIST
	public List<AccountManage> selectAccountManageList(Map map) {
		return sqlSessionTemplate.selectList("selectAccountManageList", map);
	}

	@Override//详情
	public AccountManage selectAccountManage(AccountManage accountManage) {
		return (AccountManage) sqlSessionTemplate.selectOne("selectAccountManage", accountManage);
	}

	@Override//修改金额
	public int updateAccountManage(AccountManage accountManage) {
		return sqlSessionTemplate.update("updateAccountManage", accountManage);
	}

	/**
	 * 修改预存款状态
	 */
	@Override
	public void updateAccountManageByStatus(AccountManage manage) {
		sqlSessionTemplate.update("updateAccountManageByStatus", manage);
		
	}

}
