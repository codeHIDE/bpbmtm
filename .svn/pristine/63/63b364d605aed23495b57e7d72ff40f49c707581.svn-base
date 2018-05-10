package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.SysOpLogDao;
import com.bypay.domain.SysOpLog;

@Repository("sysOpLogDao")
public class SysOpLogDaoImpl implements SysOpLogDao{

	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	/**
	 * 添加日志操作
	 */
	@Override
	public void insertSysOpLog(SysOpLog sysOpLog) {
		sqlSessionTemplate.insert("insertSysOpLog", sysOpLog);
		
	}
	@Override
	public List<SysOpLog> selectSysOpLog(Map map) {
		return sqlSessionTemplate.selectList("selectSysOpLog",map);
	}
	@Override
	public Integer selectSysOpLogCount(Map map) {
		return (Integer) sqlSessionTemplate.selectOne("selectSysOpLogCount",map);
	}

}
