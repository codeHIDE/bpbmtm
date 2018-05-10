package com.bypay.dao;

import java.util.List;
import java.util.Map;
import com.bypay.domain.AppTractInfo;

public interface AppTractInfoDao {
	
	//添加应用通道
	int insertAppTractInfo(AppTractInfo appTractInfo);
	
	//查询添加时候的通道号是否存在
	AppTractInfo selectAppTractInfoRepeat(String transMerId);
	
	//应用通道查询 查询所有通道信息
	List<AppTractInfo> selectAppTractInfoList(Map map);
	
	//查询应用通道的条数 分页用
	int selectAppTractInfoCount(AppTractInfo appTractInfo);
	
	//应用通道详情
	AppTractInfo selectAppTractById(String tractId);
	
	//应用通道启动更新
	int updateAppTractInfo(AppTractInfo appTractInfo);
	
	//应用通道暂停更新
	int updateAppTractInfoOFF(AppTractInfo appTractInfo);
	
	//应用修改通道信息
	int updateAppTract(AppTractInfo appTractInfo);

	List<AppTractInfo>  selectAppTractInfoListByTractType(AppTractInfo appTractInfo);
}
