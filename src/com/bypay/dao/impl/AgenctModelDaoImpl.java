package com.bypay.dao.impl;

import java.util.*;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.AgenctModelDao;
import com.bypay.domain.AgenctModel;
@Repository("AgenctModelDao")
public class AgenctModelDaoImpl implements AgenctModelDao{
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<AgenctModel> selectAgenctModelAll(Map map) {
		return sqlSessionTemplate.selectList("selectAgenctModelAll",map);
	}
	@Override
	public boolean deleteAgenctModel(AgenctModel a) {
		if(sqlSessionTemplate.delete("deleteAgenctModel",a)>0)
			return true;
		else
			return false;
	}
	@Override
	public boolean insertAgenctModel(AgenctModel a) {
		if(sqlSessionTemplate.insert("insertAgenctModel",a)>0)
			return true;
		else
			return false;
	}
	@Override
	public AgenctModel selectAgenctModel(AgenctModel a) {
		return (AgenctModel) sqlSessionTemplate.selectOne("selectAgenctModel",a);
	}
	@Override
	public boolean updateAgenctModel(AgenctModel a) {
		if(sqlSessionTemplate.update("updateAgenctModel",a)>0)
			return true;
		else
			return false;
	}
	@Override
	public Integer selectAgenctModelCount() {
		return (Integer) sqlSessionTemplate.selectOne("selectAgenctModelCount");
	}
}
