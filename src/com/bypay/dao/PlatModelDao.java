package com.bypay.dao;

import java.util.List;
import java.util.Map;

import com.bypay.domain.PlatModel;

public interface PlatModelDao {
	List<PlatModel> selectPlatModel();
	
	List<PlatModel> selectPlatModelList(Map map);
	
	Integer selectPlatModelListCount(Map map);
	
	Integer deletePlatModel(String id);
	
	Integer updatePlatModel(PlatModel platModel);
	
	Integer insertPlatModel(PlatModel platModel);
	
	PlatModel selectPlatModelById(String id);
}
