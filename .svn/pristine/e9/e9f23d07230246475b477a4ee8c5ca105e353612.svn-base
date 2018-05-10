package com.bypay.dao.impl;

import java.util.*;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.TerminalBatchConfigDao;
import com.bypay.domain.TerminalBatchConfig;

@Repository("TerminalBatchConfigDao")
public class TerminalBatchConfigDaoImpl implements TerminalBatchConfigDao {

	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public boolean insertTerminalBatchConfig(
			TerminalBatchConfig terminalBatchConfig) {
		if(sqlSessionTemplate.insert("insertTerminalBatchConfig", terminalBatchConfig)>0)
			return true;
		else
			return false;
	}
	@SuppressWarnings("unchecked")
	public List<TerminalBatchConfig> selectTerminalBatchConfig(
			Map terminalBatchConfig) {
		return sqlSessionTemplate.selectList("selectTerminalBatchConfig", terminalBatchConfig);
	}
	@SuppressWarnings("unchecked")
	public Integer selectCountTerminalBatchConfig(Map terminalBatchConfig) {
		return (Integer) sqlSessionTemplate.selectOne("selectCountTerminalBatchConfig", terminalBatchConfig);
	}
	@Override
	public Integer updateTerminalBatchConfig(
			TerminalBatchConfig terminalBatchConfig) {
		return sqlSessionTemplate.update("updateTerminalBatchConfig",terminalBatchConfig);
	}

}
