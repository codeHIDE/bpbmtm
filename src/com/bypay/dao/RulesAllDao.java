package com.bypay.dao;

import java.util.List;
import java.util.Map;

import com.bypay.domain.RulesAll;

public interface RulesAllDao {
	List<RulesAll> selectRulesAllList(Map map);
	
	Integer selectRulesAllListCount(Map map);
	
	Integer deleteRulesAllById(RulesAll rulesAll);
	
	Integer insertRulesAll(RulesAll rulesAll);
	
	Integer updateRulesAllStatus(RulesAll rulesAll);
}
