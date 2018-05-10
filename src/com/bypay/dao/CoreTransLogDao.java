package com.bypay.dao;

import java.util.List;
import java.util.Map;

import com.bypay.domain.CoreTransLog;
public interface CoreTransLogDao
{
    Map selectCoreTransLogCount(Map map);
	  
	 List<CoreTransLog> selectCoreTransLog(Map map);
	 
	 CoreTransLog selectInfo(CoreTransLog coreTransLog);
	 
	 List<CoreTransLog> selectInfoList(CoreTransLog coreTransLog);
}
