package com.bypay.dao.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.CdateConfigDao;
import com.bypay.domain.CdateConfig;
@Repository("CdateConfigDao")
public class CdateConfigDaoImpl implements CdateConfigDao {
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public Integer deleteCdateConfig() {
		return sqlSessionTemplate.delete("deleteCdateConfig");
	}

	@Override
	public Integer insertCdateConfig(CdateConfig cdateConfig) {
		return sqlSessionTemplate.insert("insertCdateConfig",cdateConfig);
	}

}
