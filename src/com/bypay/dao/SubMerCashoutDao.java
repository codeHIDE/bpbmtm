package com.bypay.dao;

import java.util.List;
import java.util.Map;

import com.bypay.domain.SubMerCashout;

public interface SubMerCashoutDao {
	List<SubMerCashout> selectSubMerCashoutReport(Map map);
	
	List<SubMerCashout> selectSubMerCashoutAmt(Map map);
	
	Integer selectSubMerCashoutReportCount(Map map);
	
  SubMerCashout selectSubMerCashoutById(String id);
  
  SubMerCashout selectSubMerCashoutByOrderId(String orderId);
  
  List<SubMerCashout> selectSubMerCashout();

  Integer insertSubMerCashout(SubMerCashout subMerCashout);

  List<SubMerCashout> selectSubMerCashoutList(Map<String, Object> map);

  Integer selectSubMerCashoutListCount(Map<String, Object> map);
  
  List<SubMerCashout> selectSubMerCashoutList3(Map<String, Object> map);
  
  Integer selectSubMerCashoutListCount3(Map<String, Object> map);

  /*
   * 删除
   */
  Integer deleteSubMerCashout(SubMerCashout subMerCashout);

  /**
   * 根据商户号获取提现记录总条数
   * 
   * @param map
   * @return
   */
  Integer getSubMerCashoutBySubIdCount(Map map);

  /**
   * 根据商户号获取提现记录
   * 
   * @param map
   * @return
   */
  List<SubMerCashout> getSubMerCashoutBySubId(Map map);

  List<SubMerCashout> selectSubMerCashoutListNotPage(Map<String, Object> map);

  Integer updateSubMerCashout(SubMerCashout subMerCashout);
  Integer updateSubMerCashoutByBatch(SubMerCashout subMerCashout);
  
  	//通道查询 查询所有SubMerCashout信息LIST
	List<SubMerCashout> getSubMerCashoutList(Map map);
	
	//查询SubMerCashout的条数 分页用
	int getSubMerCashoutListCount(SubMerCashout subMerCashout);
	
	//查询SubMerCashout信息LIST
	List<SubMerCashout> getSubMerCashoutListByOrderStatus(SubMerCashout subMerCashout);

	Integer selectSubMerCashoutListNotPageCount(Map<String, Object> map);
	
	List<SubMerCashout> seleSubMerCashoutReport(
			SubMerCashout subMerCashout);

	/**
	 * 查询商户的提现信息
	 * @param subMerCashout
	 * @return
	 */
	List<SubMerCashout> selectSubMerCashoutList2(SubMerCashout subMerCashout);
}
