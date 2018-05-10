package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.MerInfoUpdateDao;
import com.bypay.domain.MerInfoUpdate;
@Repository("MerInfoUpdateDao")
public class MerInfoUpdateDaoImpl implements MerInfoUpdateDao {
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public List<MerInfoUpdate> selectMerInfoUpdate(Map map) {
		return sqlSessionTemplate.selectList("selectSubMerUpdate",map);
	}

	@Override
	public MerInfoUpdate selectMerInfoUpdateById(String id) {
		return (MerInfoUpdate) sqlSessionTemplate.selectOne("selectSubMerUpdateById",id);
	}

	@Override
	public Integer selectMerInfoUpdateCount(Map map) {
		return (Integer) sqlSessionTemplate.selectOne("selectSubMerUpdateCount",map);
	}

	@Override
	public Integer updateMerInfoUpdateStatus(MerInfoUpdate merInfoUpdate) {
		return sqlSessionTemplate.update("updateSubMerUpdateStatus",merInfoUpdate);
	}

	@Override
	public List<MerInfoUpdate> selectMerInfoUpdateStatus() {
		return sqlSessionTemplate.selectList("selectMerInfoUpdateStatus");
	}
	@Override
	public Integer insertMerInfoUpdate(MerInfoUpdate subMerUpdate) {
		return sqlSessionTemplate.insert("insertMerInfoUpdate",subMerUpdate);
	}

}
