package com.bypay.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.MerAppTractDao;
import com.bypay.domain.MerAppTract;
@Repository("MerAppTractDao")
public class MerAppTractDaoImpl implements MerAppTractDao {
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	@Override
	public List<MerAppTract> selectMerAppTractByMerSysId(MerAppTract merAppTract) {
		return sqlSessionTemplate.selectList("selectMerAppTractByMerSysId",merAppTract);
	}
	@Override
	public Integer insertMerAppTract(MerAppTract merAppTract) {
		return sqlSessionTemplate.insert("insertMerAppTract",merAppTract);
	}
	@Override
	public Integer updateMerAppTractStatus(MerAppTract merAppTract) {
		return sqlSessionTemplate.update("updateMerAppTractStatus",merAppTract);
	}
	@Override
	public MerAppTract selectMerAppTractByTractId(MerAppTract merAppTract) {
		return (MerAppTract) sqlSessionTemplate.selectOne("selectMerAppTractByTractId",merAppTract);
	}
	@Override
	public Integer updateMerAppTract(MerAppTract merAppTract) {
		return sqlSessionTemplate.update("updateMerAppTract",merAppTract);
	}
}
