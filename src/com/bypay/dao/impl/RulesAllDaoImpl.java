package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.RulesAllDao;
import com.bypay.domain.RulesAll;
@Repository("RulesAllDao")
public class RulesAllDaoImpl implements RulesAllDao {
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public Integer deleteRulesAllById(RulesAll rulesAll) {
		return sqlSessionTemplate.delete("deleteRulesAllById",rulesAll);
	}

	@Override
	public Integer insertRulesAll(RulesAll rulesAll) {
		return sqlSessionTemplate.insert("insertRulesAll",rulesAll);
	}

	@Override
	public List<RulesAll> selectRulesAllList(Map map) {
		return sqlSessionTemplate.selectList("selectRulesAllList",map);
	}

	@Override
	public Integer selectRulesAllListCount(Map map) {
		return (Integer) sqlSessionTemplate.selectOne("selectRulesAllListCount",map);
	}

	@Override
	public Integer updateRulesAllStatus(RulesAll rulesAll) {
		return sqlSessionTemplate.update("updateRulesAllStatus",rulesAll);
	}

}
