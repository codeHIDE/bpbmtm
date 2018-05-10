package com.bypay.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.CreditInfoDao;
import com.bypay.domain.CreditInfo;

@Repository("creditInfoDao")
public class CreditInfoDaoImpl implements CreditInfoDao{
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public int insertCredit(CreditInfo creditInfo) {
		return sqlSessionTemplate.insert("insertCredit",creditInfo);
	}

	@Override
	public int deleteCredit(CreditInfo creditInfo) {
		return sqlSessionTemplate.delete("deleteCredit",creditInfo);
	}

	@Override
	public List<CreditInfo> selectCreditInfoBySubMerId(
			CreditInfo creditInfo) {
		return sqlSessionTemplate.selectList("selectCreditInfoBySubMerId", creditInfo);
	}

}
