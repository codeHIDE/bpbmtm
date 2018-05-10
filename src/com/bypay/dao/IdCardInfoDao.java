package com.bypay.dao;

import java.util.List;
import java.util.Map;

import com.bypay.domain.IdCardInfo;


public interface IdCardInfoDao {
	
	 Integer insertIdCardInfo(IdCardInfo idCardInfo);
	 
	  List<IdCardInfo> selectIdCardInfo(Map map);

	  Integer selectIdCardInfoCount(Map map);
	  
	  IdCardInfo selectOne(IdCardInfo idCardInfo);
	  
	  
}
