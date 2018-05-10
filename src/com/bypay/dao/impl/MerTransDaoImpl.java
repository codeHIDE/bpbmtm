package com.bypay.dao.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.MerTransDao;
import com.bypay.domain.MerTrans;
@Repository("merTransDao")
public class MerTransDaoImpl implements MerTransDao {
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	@Override
	public MerTrans selectMerTransByMerSysId(String merSysId) {
		return (MerTrans) sqlSessionTemplate.selectOne("selectMerTransByMerSysId",merSysId);
	}
	@Override
	public Integer insertMerTrans(MerTrans merTrans) {
		return sqlSessionTemplate.insert("insertMerTrans",merTrans);
	}
	@Override
	public Integer updateMerTrans(MerTrans merTrans) {
		return sqlSessionTemplate.update("updateMerTrans",merTrans);
	}
	/**
	 * 获取机构交易配置信息
	 */
	@Override
	public MerTrans getMerTransInfo(String merSysId) {
		return (MerTrans) sqlSessionTemplate.selectOne("getMerTransInfo",merSysId);
	}
	@Override
	public Integer updateMerTransRules(MerTrans merTrans) {
		return sqlSessionTemplate.update("updateMerTransRules",merTrans);
	}
}
