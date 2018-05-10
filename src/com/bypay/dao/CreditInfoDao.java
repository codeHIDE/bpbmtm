package com.bypay.dao;

import java.util.List;

import com.bypay.domain.CreditInfo;


public interface CreditInfoDao {
	//添加厂商表
		int insertCredit(CreditInfo CreditInfo);
		
		int deleteCredit(CreditInfo CreditInfo);
		
	    List<CreditInfo> selectCreditInfoBySubMerId(CreditInfo creditInfo);

}
