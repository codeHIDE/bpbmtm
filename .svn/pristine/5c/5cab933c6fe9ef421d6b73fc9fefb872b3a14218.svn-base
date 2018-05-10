package com.bypay.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bypay.dao.AccountManageDao;
import com.bypay.domain.AccountManage;
import com.bypay.service.AccountManageService;

@Service("accountManageService")
public class AccountManageServiceImpl implements AccountManageService{

	
	@Inject
	private AccountManageDao accountManageDao;
	
	@Override//条数 
	public int selectAccountManageCount(AccountManage accountManage) {
		return accountManageDao.selectAccountManageCount(accountManage);
	}

	@Override// LIST 分页 
	public List<AccountManage> selectAccountManageList(Map map) {
		return accountManageDao.selectAccountManageList(map);
	}

	@Override//详情
	public AccountManage selectAccountManage(AccountManage accountManage) {
		return accountManageDao.selectAccountManage(accountManage);
	}

	@Override//修改
	public int updateAccountManage(AccountManage accountManage) {
		return accountManageDao.updateAccountManage(accountManage);
	}

}
