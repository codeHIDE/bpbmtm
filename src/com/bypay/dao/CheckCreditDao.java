package com.bypay.dao;

import java.util.List;

import com.bypay.domain.CheckCredit;


public interface CheckCreditDao {
	//添加厂商表
		int insertCredit(CheckCredit checkCredit);
		
		int deleteCredit(CheckCredit checkCredit);
		
	    List<CheckCredit> selectCheckCredit(CheckCredit CheckCredit);
	    
	    int selectChecked(CheckCredit CheckCredit);

}
