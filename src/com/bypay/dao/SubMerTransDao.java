package com.bypay.dao;

import com.bypay.domain.SubMerTrans;

public interface SubMerTransDao {
	SubMerTrans selectSubMerTransById(SubMerTrans smt);

	/**
	 * 添加子商户交易配置信息
	 * @param map
	 * @return
	 */
	int insertSubMerTransInfo(SubMerTrans subMerTrans);

	/**
	 * 修改子商户交易配置信息
	 * @param subMerTrans
	 */
	int updateSubMerTransInfo(SubMerTrans subMerTrans);
	
	int deleteSubMerTransInfo(SubMerTrans subMerTrans);
	
}
