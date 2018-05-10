package com.bypay.service;

import java.util.List;
import java.util.Map;

import com.bypay.domain.SubMerRate;

public interface SubMerRateService {
	//查询指定的单条子商户交易费率信息，用于子商户的详情显示
	SubMerRate selectSubMerRateGetById(SubMerRate sr);

	/**
	 * 添加子商户费率信息
	 * @param map
	 * @return
	 */
	int insertSubMerRateInfo(Map<Object, Object> map);
	
	List<SubMerRate> selectSubMerRateById(SubMerRate subMerRate);
	
	Integer updateSubMerRate(SubMerRate subMerRate);
	
	void updateSubMerRateRateType();
	
	public SubMerRate getSubMerRateBySubMerId(SubMerRate sr);
}
