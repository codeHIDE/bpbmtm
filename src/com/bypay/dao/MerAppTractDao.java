package com.bypay.dao;

import java.util.List;

import com.bypay.domain.MerAppTract;

public interface MerAppTractDao {
	List<MerAppTract> selectMerAppTractByMerSysId(MerAppTract merAppTract);
	
	Integer insertMerAppTract(MerAppTract merAppTract);
	
	Integer updateMerAppTractStatus(MerAppTract merAppTract);
	
	MerAppTract selectMerAppTractByTractId(MerAppTract merAppTract);
	
	Integer updateMerAppTract(MerAppTract merAppTract);
}
