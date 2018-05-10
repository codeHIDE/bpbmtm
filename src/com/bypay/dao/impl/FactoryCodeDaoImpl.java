package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Repository;
import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.FactoryCodeDao;
import com.bypay.domain.FactoryCode;

@Repository("factoryCodeDao")
public class FactoryCodeDaoImpl implements FactoryCodeDao{
	
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;

//	@Override
//	public List<FactoryCode> selectFactoryCode() {
//		return sqlSessionTemplate.selectList("selectFactoryCode");
//	}

	
	//添加厂商
	@Override
	public int insertFactoryCode(FactoryCode factoryCode) {
		return sqlSessionTemplate.insert("insertFactoryCodeOne", factoryCode);
	}

	//查询厂商列表
	@Override
	public List<FactoryCode> selectFactoryCodeList(Map map) {
		return sqlSessionTemplate.selectList("selectFactoryCodeList", map);
	}

	//查询厂商列表 条数分页用
	@Override
	public int selectFactoryCodeListCount(FactoryCode factoryCode) {
		return (Integer) sqlSessionTemplate.selectOne("selectFactoryCodeListCount", factoryCode);
	}

	//删除厂商
	@Override
	public int deleteFactoryCode(FactoryCode factoryCode) {
		return sqlSessionTemplate.delete("deleteFactoryCode", factoryCode);
	}


}
