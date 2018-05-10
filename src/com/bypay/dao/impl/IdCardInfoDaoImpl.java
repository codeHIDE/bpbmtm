package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.IdCardInfoDao;
import com.bypay.domain.IdCardInfo;

@Repository("IdCardInfoDao")
public class IdCardInfoDaoImpl  implements IdCardInfoDao{
	
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public Integer insertIdCardInfo(IdCardInfo idCardInfo) {
		return sqlSessionTemplate.insert("insertIdCardInfo",idCardInfo);
	}

	@Override
	public List<IdCardInfo> selectIdCardInfo(Map map) {
		return sqlSessionTemplate.selectList("selectIdCardInfo",map);
	}
	@Override
	public Integer selectIdCardInfoCount(Map map) {
		return (Integer) sqlSessionTemplate.selectOne("selectIdCardInfoCount",map);
	}

	@Override
	public IdCardInfo selectOne(IdCardInfo idCardInfo) {
		return (IdCardInfo) sqlSessionTemplate.selectOne("selectOne",idCardInfo);
	}
	
}
