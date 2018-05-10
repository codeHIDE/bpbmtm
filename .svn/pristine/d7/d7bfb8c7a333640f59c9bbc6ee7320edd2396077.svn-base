package com.bypay.dao;

import java.util.List;
import java.util.Map;

import com.bypay.domain.TractInfo;

public interface TractInfoDao {
	
	//添加通道
	int insertTractInfo(TractInfo tractInfo);
	
	//查询添加时候的通道号是否存在
	TractInfo selectTractInfoRepeat(String transMerId);
	
	//通道查询 查询所有通道信息
	List<TractInfo> selectTractInfoList(Map map);
	
	//查询通道的条数 分页用
	int selectTractInfoCount(TractInfo TractInfo);
	
	//通道详情
	TractInfo selectTractById(String tractId);
	
	//通道启动更新
	int updateTractInfo(TractInfo tractInfo);
	
	//通道暂停更新
	int updateTractInfoOFF(TractInfo tractInfo);
	
	//修改通道信息
	int updateTract(TractInfo tractInfo);
	
	List<TractInfo> selectTractInfoByRatesType(TractInfo tractInfo);
	
	//跑批用
	List<TractInfo> selectTractInfoAllSettle(TractInfo tractInfo);
	
	//子商户上线查询通道
	List<TractInfo> selectTractInfoSubMerInfoOnline();

    List<TractInfo> selectTractInfoByStatus(Map<String, Object> map);
    
    //通道启动更新
	Integer updateReserved(TractInfo tractInfo);
	
	//通道详情
	TractInfo selectTractByMerId(String transMerId);
}
