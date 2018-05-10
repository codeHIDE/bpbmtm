package com.bypay.dao.impl;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.ProcedureDao;
@Repository("procedureDao")
public class ProcedureDaoImpl implements ProcedureDao {
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public Integer getSequence(Map map) throws Exception{
		return (Integer) sqlSessionTemplate.selectOne("getSequence",map);
	}
	@Override
	public String getOrderId() throws Exception {
		return String.valueOf( sqlSessionTemplate.selectOne("getOrderId","orderInfo"));
	}
	

}
