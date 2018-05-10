package com.bypay.dao;

import com.bypay.domain.SubMerAuthInfo;

public interface SubMerAuthInfoDao {

	/**
	 * 添加实人认证信息
	 * @param auth
	 * @return
	 */
	int addSubMerAuthInfo(SubMerAuthInfo auth);

	/**
	 * 修改实人认证信息
	 * @param info
	 */
	boolean updateSubMerAuthInfo(SubMerAuthInfo info);

	/**
	 * 修改实人认证AUTH_STATUS字段
	 * @param subMerAuthInfo
	 */
	void updateSubMerAuthInfoByStatus(SubMerAuthInfo subMerAuthInfo);

}
