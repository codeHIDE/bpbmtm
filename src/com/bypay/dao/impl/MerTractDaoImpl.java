package com.bypay.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.MerTractDao;
import com.bypay.domain.MerTract;
@Repository("MerTractDao")
public class MerTractDaoImpl implements MerTractDao {
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	@Override
	public List<MerTract> selectmerTractByMerSysId(String merSysId) {
		return sqlSessionTemplate.selectList("selectmerTractByMerSysId",merSysId);
	}
	@Override
	public Integer insertMerTract(MerTract merTract) {
		return sqlSessionTemplate.insert("insertMerTract",merTract);
	}
	@Override
	public Integer insertMerTractInfo(MerTract merTract) {
		return sqlSessionTemplate.insert("insertMerTractInfo",merTract);
	}
	@Override
	public Integer updateMerTrackDefaultStatus(MerTract merTract) {
		return sqlSessionTemplate.update("updateMerTrackDefaultStatus", merTract);
	}
	@Override
	public Integer updateMerTractStatus(MerTract merTract) {
		return sqlSessionTemplate.update("updateMerTractStatus",merTract);
	}
	@Override
	public Integer updateAllMerTrackDefaultStatus(MerTract merTract) {
		return sqlSessionTemplate.update("updateAllMerTrackDefaultStatus",merTract);
	}
	@Override
	public List<MerTract> selectmerTractByTractId(MerTract merTract) {
		return sqlSessionTemplate.selectList("selectmerTractByTractId",merTract);
	}
	@Override
	public Integer tractAndMerToUpdate(MerTract merTract) {
		return sqlSessionTemplate.update("tractAndMerToUpdate",merTract);
	}
}
