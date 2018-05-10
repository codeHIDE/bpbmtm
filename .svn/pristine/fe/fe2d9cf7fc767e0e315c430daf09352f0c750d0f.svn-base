package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.MerModelDao;
import com.bypay.domain.MerModel;
@Repository("MerModelDao")
public class MerModelDaoImpl implements MerModelDao {
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	@Override
	public List<MerModel> selectMerModel() {
		return sqlSessionTemplate.selectList("selectMerModel");
	}
	@Override
	public Integer deleteMerModel(String id) {
		return sqlSessionTemplate.delete("deleteMerModel",id);
	}
	@Override
	public Integer insertMerModel(MerModel merModel) {
		return sqlSessionTemplate.insert("insertMerModel",merModel);
	}
	@Override
	public Integer updateMerModel(MerModel merModel) {
		return sqlSessionTemplate.update("updateMerModel",merModel);
	}
	@Override
	public List<MerModel> selectMerModelList(Map map) {
		return sqlSessionTemplate.selectList("selectMerModelList",map);
	}
	@Override
	public Integer selectMerModelListCount(Map map) {
		return (Integer) sqlSessionTemplate.selectOne("selectMerModelListCount",map);
	}
	@Override
	public MerModel selectMerModelById(String id) {
		return (MerModel) sqlSessionTemplate.selectOne("selectMerModelById",id);
	}
}
