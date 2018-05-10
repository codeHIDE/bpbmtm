package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.PayBankCodeDao;
import com.bypay.domain.PayBankCode;

@Repository("PayBankCodeDao")
public class PayBankCodeDaoImpl implements PayBankCodeDao {

	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public boolean insertPayBankCode(PayBankCode pbd) {
		if(sqlSessionTemplate.insert("insertPayBankCode",pbd)>0)
			return true;
		else
			return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PayBankCode> selectPayBankCodeAll(Map<String, Object> map) {
		return sqlSessionTemplate.selectList("selectPayBankCodeAll", map);
	}

	public boolean deletePayBankCode(PayBankCode pbc){
		if(sqlSessionTemplate.delete("deletePayBankCode", pbc)>0){
			return true;
		}else{
			return false;
		}
	}
	public Integer selectPayBankCodeCount(){
		return (Integer) sqlSessionTemplate.selectOne("selectPayBankCodeCount");
	}
	
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
