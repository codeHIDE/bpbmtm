package com.bypay.dao;

import com.bypay.domain.MerRisk;

public interface MerRiskDao {
	MerRisk selectmerRiskByMerSysId(String merSysId);
	
	Integer inserMerRisk(MerRisk merRisk);
	
	Integer updateMerRisk(MerRisk merRisk);
}
