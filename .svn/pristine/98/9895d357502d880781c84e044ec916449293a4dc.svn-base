package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.FactoryRiskDao;
import com.bypay.domain.FactoryRisk;
@Repository("FactoryRiskDao")
public class FactoryRiskDaoImpl implements FactoryRiskDao{
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public Integer deleteFactoryRisk(String id) {
		return sqlSessionTemplate.delete("deleteFactoryRisk",id);
	}

	@Override
	public Integer insertFactoryRisk(FactoryRisk factoryRisk) {
		return sqlSessionTemplate.insert("insertFactoryRisk",factoryRisk);
	}

	@Override
	public Integer selectFactoryRiskCount() {
		return (Integer) sqlSessionTemplate.selectOne("selectFactoryRiskCount");
	}

	@Override
	public FactoryRisk selectFactoryRiskById(FactoryRisk factoryRisk) {
		return (FactoryRisk) sqlSessionTemplate.selectOne("selectFactoryRiskById",factoryRisk);
	}

	@Override
	public List<FactoryRisk> selectFactoryRiskList(Map map) {
		return sqlSessionTemplate.selectList("selectFactoryRiskList",map);
	}

	@Override
	public Integer updateFactoryRisk(FactoryRisk factoryRisk) {
		return sqlSessionTemplate.update("updateFactoryRisk",factoryRisk);
	}

}
