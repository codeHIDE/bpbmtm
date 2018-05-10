package com.bypay.dao.impl;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.BankBehalfBranchDao;
import com.bypay.domain.BankBehalfBranch;
@Repository("BankBehalfBranchDao")
public class BankBehalfBranchDaoImpl implements BankBehalfBranchDao {
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public List<BankBehalfBranch> selectBankBehalfBranchList() {
		return sqlSessionTemplate.selectList("selectBankBehalfBranchList");
	}
	
	
}
