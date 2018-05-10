package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.RegionCodeDao;
import com.bypay.domain.RegionCode;
@Repository("RegionCodeDao")
public class RegionCodeDaoImpl implements RegionCodeDao {
	
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	
	//添加对应表
	@Override
	public int insertRegionCode(RegionCode regionCode) {
		return sqlSessionTemplate.insert("insertRegionCode", regionCode);
	}

	//查询对应表列表
	@Override
	public List<RegionCode> selectRegionCodeList(Map map) {
		return sqlSessionTemplate.selectList("selectRegionCodeList", map);
	}

	//查询对应表列表 条数分页用
	@Override
	public int selectRegionCodeListCount(RegionCode regionCode) {
		return (Integer) sqlSessionTemplate.selectOne("selectRegionCodeListCount", regionCode);
	}

	//删除样式
	@Override
	public int deleteRegionCode(RegionCode regionCode) {
		return sqlSessionTemplate.delete("deleteRegionCode", regionCode);
	}

	@Override
	public List<RegionCode> selectRegionCodeList2(RegionCode regionCode) {
		return sqlSessionTemplate.selectList("selectRegionCodeList2", regionCode);
	}

	@Override
	public List<RegionCode> selectRegionCodeListAll() {
	    return sqlSessionTemplate.selectList("selectRegionCodeAll");
	}

}
