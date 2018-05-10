package com.bypay.dao.impl;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.BusTypeDao;
import com.bypay.domain.BusType;

@Repository("busTypeDao")
public class BusTypeDaoImpl implements BusTypeDao{
	
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	//获取所有产品类别
	@Override
	public List<BusType> selectBusTypeAll(){
		return sqlSessionTemplate.selectList("selectBusTypeAll");
	}
	
	//添加对应表
	@Override
	public int insertBusType(BusType busType) {
		return sqlSessionTemplate.insert("insertBusType", busType);
	}

	//查询对应表列表
	@Override
	public List<BusType> selectBusTypeList(Map map) {
		return sqlSessionTemplate.selectList("selectBusTypeList", map);
	}

	//查询对应表列表 条数分页用
	@Override
	public int selectBusTypeListCount(BusType busType) {
		return (Integer) sqlSessionTemplate.selectOne("selectBusTypeListCount", busType);
	}

	//删除样式
	@Override
	public int deleteBusType(BusType busType) {
		return sqlSessionTemplate.delete("deleteBusType", busType);
	}

}
