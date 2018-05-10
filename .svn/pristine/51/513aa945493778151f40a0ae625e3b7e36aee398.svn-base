package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.PlatMerInfoDao;
import com.bypay.domain.PlatMerInfo;
@Repository("PlatMerInfoDao")
public class PlatMerInfoDaoImpl implements PlatMerInfoDao {
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	@Override
	public Integer insertPlatMerInfo(PlatMerInfo platMerInfo) {
		return sqlSessionTemplate.insert("insertPlatMerInfo",platMerInfo);
	}
	@Override
	public List<PlatMerInfo> selectPlatMerInfoAll(Map map) {
		return sqlSessionTemplate.selectList("selectPlatMerInfoAll", map);
	}
	@Override
	public Integer selectPlatMerInfoCount(PlatMerInfo platMerInfo) {
		return (Integer) sqlSessionTemplate.selectOne("selectPlatMerInfoCount", platMerInfo);
	}
	@Override
	public Integer approvalPlatMerInfo(PlatMerInfo platMerInfo) {
		return sqlSessionTemplate.update("approvalPlatMerInfo",platMerInfo);
	}
	@Override
	public PlatMerInfo selectPlatMerInfoById(PlatMerInfo platMerInfo) {
		return (PlatMerInfo) sqlSessionTemplate.selectOne("selectPlatMerInfoById",platMerInfo);
	}
	@Override
	public Integer updatePlatMerInfo(PlatMerInfo platMerInfo) {
		return sqlSessionTemplate.update("updatePlatMerInfo",platMerInfo);
	}
	@Override
	public List<PlatMerInfo> selectPlatMerInfoList() {
		return sqlSessionTemplate.selectList("selectPlatMerInfoList");
	}
	
}
