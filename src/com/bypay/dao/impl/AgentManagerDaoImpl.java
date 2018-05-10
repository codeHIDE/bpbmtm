package com.bypay.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.AgentManagerDao;
import com.bypay.domain.AgentManager;

@Repository("agentManagerDao")
public class AgentManagerDaoImpl implements AgentManagerDao{

	@Inject
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public Integer updateAgenctInfoPassword(AgentManager agentManager) {
		return sqlSessionTemplate.update("updateAgenctInfoPassword", agentManager);
	}


}
