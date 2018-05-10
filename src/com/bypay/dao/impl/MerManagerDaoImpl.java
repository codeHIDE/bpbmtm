package com.bypay.dao.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.MerManagerDao;
import com.bypay.domain.MerManager;
@Repository("MerManagerDao")
public class MerManagerDaoImpl implements MerManagerDao {
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	@Override
	public Integer insertMerManager(MerManager merManager) {
		return sqlSessionTemplate.insert("insertMerManager",merManager);
	}
	@Override
	public Integer selectMerManagerNameByMid(MerManager merManager) {
		return (Integer) sqlSessionTemplate.selectOne("selectMerManagerNameByMid",merManager);
	}
	@Override
	public MerManager selectMerManagerRemarkByMid(MerManager merManager) {
		return (MerManager) sqlSessionTemplate.selectOne("selectMerManagerRemarkByMid",merManager);
	}
	@Override
	public Integer updateMerManager(MerManager merManager) {
		return sqlSessionTemplate.update("updateMerManager",merManager);
	}
	@Override
	public Integer updateMerManagerPassword(MerManager merManager) {
		return sqlSessionTemplate.update("updateMerManagerPassword", merManager);
	}
}
