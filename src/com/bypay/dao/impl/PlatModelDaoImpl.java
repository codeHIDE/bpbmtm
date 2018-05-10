package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.PlatModelDao;
import com.bypay.domain.PlatModel;
@Repository("PlatModelDao")
public class PlatModelDaoImpl implements PlatModelDao {
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public Integer deletePlatModel(String id) {
		return sqlSessionTemplate.delete("deletePlatModel",id);
	}

	@Override
	public Integer insertPlatModel(PlatModel platModel) {
		return sqlSessionTemplate.insert("insertPlatModel",platModel);
	}

	@Override
	public List<PlatModel> selectPlatModel() {
		return sqlSessionTemplate.selectList("selectPlatModel");
	}

	@Override
	public PlatModel selectPlatModelById(String id) {
		return (PlatModel) sqlSessionTemplate.selectOne("selectPlatModelById",id);
	}

	@Override
	public List<PlatModel> selectPlatModelList(Map map) {
		return sqlSessionTemplate.selectList("selectPlatModelList",map);
	}

	@Override
	public Integer selectPlatModelListCount(Map map) {
		return (Integer) sqlSessionTemplate.selectOne("selectPlatModelListCount",map);
	}

	@Override
	public Integer updatePlatModel(PlatModel platModel) {
		return sqlSessionTemplate.update("updatePlatModel",platModel);
	}

}
