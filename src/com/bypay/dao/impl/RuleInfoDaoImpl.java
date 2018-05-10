package com.bypay.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.RuleInfoDao;
import com.bypay.domain.RuleInfo;
@Repository("ruleInfoDao")
public class RuleInfoDaoImpl implements RuleInfoDao {
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int insertRuleInfo(RuleInfo ruleInfo) {
		 return sqlSessionTemplate.insert("insertRuleInfo", ruleInfo);
	}

	@Override
	public List<RuleInfo> getChildren(RuleInfo ruleInfo) {
		 return (List<RuleInfo>)sqlSessionTemplate.selectList("getChildren", ruleInfo);
	}
	
}
