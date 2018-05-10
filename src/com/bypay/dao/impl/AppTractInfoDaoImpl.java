package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.AppTractInfoDao;
import com.bypay.domain.AppTractInfo;
import com.bypay.util.DateUtil;

@Repository("appTractInfoDao")
public class AppTractInfoDaoImpl implements AppTractInfoDao{
	
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	@Override //添加通道
	public int insertAppTractInfo(AppTractInfo appTractInfo) {
		int i = -1;
			i = sqlSessionTemplate.insert("insertAppTractInfo", appTractInfo);
		return i;

	}
	
	// 查询添加通道时候的支付商户号号是否存在
	public AppTractInfo selectAppTractInfoRepeat(String transMerId) {
		return (AppTractInfo) sqlSessionTemplate.selectOne("selectAppTractInfoRepeat", transMerId);
	}
	
	
	// 查询通道 按条件查询
	public List<AppTractInfo> selectAppTractInfoList(Map map){
		return (List<AppTractInfo>) sqlSessionTemplate.selectList("selectAppTractInfoList", map);
	}
	
	
	// 查询数据库 通道的总条数
	public int selectAppTractInfoCount(AppTractInfo appTractInfo) {
		return (Integer) sqlSessionTemplate.selectOne("selectAppTractInfoCount", appTractInfo);
	}
	
	
	// 通道详情 审批
	public AppTractInfo selectAppTractById(String tractId) {
		return (AppTractInfo) sqlSessionTemplate.selectOne("selectAppTractById", tractId);
	}

	// 更新更新通道 启动
	public int updateAppTractInfo(AppTractInfo appTractInfo) {
		return sqlSessionTemplate.update("updateAppTractInfo", appTractInfo);
	}

	// 修改商户信息
	public int updateAppTract(AppTractInfo appTractInfo) {
		return sqlSessionTemplate.update("updateAppTract", appTractInfo);
	}

	// 更新通道 暂停
	public int updateAppTractInfoOFF(AppTractInfo appTractInfo) {
		return sqlSessionTemplate.update("updateAppTractInfoOFF", appTractInfo);
	}

	@Override
	public List<AppTractInfo> selectAppTractInfoListByTractType(
			AppTractInfo appTractInfo) {
		return sqlSessionTemplate.selectList("selectAppTractInfoListByTractType",appTractInfo);
	}



}
