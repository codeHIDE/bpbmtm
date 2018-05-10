package com.bypay.dao;

import java.util.List;
import java.util.Map;

import com.bypay.domain.TransLogInfo;
public interface TransLogInfoDao
{
    Map selectTransLogInfoCount(Map map);
	  
	 List<TransLogInfo> selectTransLogInfo(Map map);
	 
}
