package com.bypay.dao;

import java.util.List;
import java.util.Map;

import com.bypay.domain.SysOpLog;

public interface SysOpLogDao {

	/**
	 * 添加日志操作
	 * @param sysOpLog
	 */
	void insertSysOpLog(SysOpLog sysOpLog);
	/**
	 * 查询日志
	 */
	List<SysOpLog> selectSysOpLog(Map map);
	Integer selectSysOpLogCount(Map map);
}
