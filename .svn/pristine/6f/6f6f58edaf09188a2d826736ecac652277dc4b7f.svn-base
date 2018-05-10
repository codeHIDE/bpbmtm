package com.bypay.dao.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.RulesMerAmtDao;
import com.bypay.domain.RulesMerAmt;
@Repository("RulesMerAmtDao")
public class RulesMerAmtDaoImpl implements RulesMerAmtDao {
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public Integer insertRulesMerAmt(RulesMerAmt rulesMerAmt) {
		return sqlSessionTemplate.insert("insertRulesMerAmt",rulesMerAmt);
	}

}
