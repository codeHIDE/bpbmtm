package com.bypay.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.CheckCreditDao;
import com.bypay.domain.CheckCredit;

@Repository("checkCreditDao")
public class CheckCreditDaoImpl implements CheckCreditDao{
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public int insertCredit(CheckCredit checkCredit) {
		return sqlSessionTemplate.insert("insertCheckCredit",checkCredit);
	}

	@Override
	public int deleteCredit(CheckCredit checkCredit) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CheckCredit> selectCheckCredit(CheckCredit CheckCredit) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int selectChecked(CheckCredit CheckCredit){
		return (Integer) sqlSessionTemplate.selectOne("selectChecked",CheckCredit);
	}
}
