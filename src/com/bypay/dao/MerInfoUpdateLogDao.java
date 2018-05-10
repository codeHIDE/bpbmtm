package com.bypay.dao;

import java.util.List;

import com.bypay.domain.MerInfoUpdateLog;

public interface MerInfoUpdateLogDao {
	boolean insertMerInfoUpdateLog(MerInfoUpdateLog miul);
	MerInfoUpdateLog selectOrgTime(MerInfoUpdateLog miul);
	
}
