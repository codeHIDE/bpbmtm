package com.bypay.dao.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.SubMerAuthInfoDao;
import com.bypay.domain.SubMerAuthInfo;

@Repository("subMerAuthInfoDao")
public class SubMerAuthInfoDaoImpl implements SubMerAuthInfoDao{

	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	/**
	 * 添加实人认证信息
	 */
	@Override
	public int addSubMerAuthInfo(SubMerAuthInfo auth) {
		return sqlSessionTemplate.insert("addSubMerAuthInfo", auth);
	}
	/**
	 * 修改实人认证信息
	 */
	@Override
	public boolean updateSubMerAuthInfo(SubMerAuthInfo info) {
		if(sqlSessionTemplate.update("updateSubMerAuthInfo", info) > 0){
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 修改实人认证AUTH_STATUS字段
	 */
	@Override
	public void updateSubMerAuthInfoByStatus(SubMerAuthInfo subMerAuthInfo) {
		sqlSessionTemplate.update("updateSubMerAuthInfoByStatus", subMerAuthInfo);
	}

}
