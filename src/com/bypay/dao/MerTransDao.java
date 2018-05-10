package com.bypay.dao;

import com.bypay.domain.MerTract;
import com.bypay.domain.MerTrans;

public interface MerTransDao {
	MerTrans selectMerTransByMerSysId(String merSysId);
	
	Integer insertMerTrans(MerTrans merTrans);
	
	Integer updateMerTrans(MerTrans merTrans);

	/**
	 * 获取机构交易配置信息
	 * @param merSysId
	 * @return
	 */
	MerTrans getMerTransInfo(String merSysId);
	
	
	Integer updateMerTransRules(MerTrans merTrans);
}
