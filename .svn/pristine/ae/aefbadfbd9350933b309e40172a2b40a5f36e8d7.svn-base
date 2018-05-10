package com.bypay.dao.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.MerRiskDao;
import com.bypay.domain.MerRisk;
@Repository("MerRiskDao")
public class MerRiskDaoImpl implements MerRiskDao {
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	@Override
	public MerRisk selectmerRiskByMerSysId(String merSysId) {
		return (MerRisk) sqlSessionTemplate.selectOne("selectmerRiskByMerSysId", merSysId);
	}
	@Override
	public Integer inserMerRisk(MerRisk merRisk) {
		return sqlSessionTemplate.insert("inserMerRisk",merRisk);
	}
	@Override
	public Integer updateMerRisk(MerRisk merRisk) {
		return sqlSessionTemplate.update("updateMerRisk",merRisk);
	}
}
