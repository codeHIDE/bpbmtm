package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.CssConfigDao;
import com.bypay.domain.CssConfig;
import com.bypay.domain.TractInfo;
@Repository("cssConfigDao")
public class CssConfigDaoImpl implements CssConfigDao {
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public List<CssConfig> selectCssConfig() {
		return sqlSessionTemplate.selectList("selectCssConfig");
	}

	
	//添加对应表
	@Override
	public int insertCssConfig(CssConfig cssConfig) {
		return sqlSessionTemplate.insert("insertCssConfig", cssConfig);
	}

	//查询对应表列表
	@Override
	public List<CssConfig> selectCssConfigList(Map map) {
		return sqlSessionTemplate.selectList("selectCssConfigList", map);
	}

	//查询对应表列表 条数分页用
	@Override
	public int selectCssConfigListCount(CssConfig cssConfig) {
		return (Integer) sqlSessionTemplate.selectOne("selectCssConfigListCount", cssConfig);
	}

	//删除样式
	@Override
	public int deleteCssConfig(CssConfig cssConfig) {
		return sqlSessionTemplate.delete("deleteCssConfig", cssConfig);
	}





}
