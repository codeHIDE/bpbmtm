package com.bypay.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.bypay.domain.MerSettleStatictis;

public interface MerSettleStatictisDao {
	
	 Integer seleteMerSettleStatictisReportCount(Map map);
	List<MerSettleStatictis> seleteMerSettleStatictisReport(Map map);
	
  List<MerSettleStatictis> selectMerSettleStatictisListAmt(MerSettleStatictis merSettleStatictis);

  Integer selectMerSettleStatictisListCount(Map<String, Object> map);

  List<MerSettleStatictis> selectMerSettleStatictisList(Map<String, Object> map);

  List<MerSettleStatictis> selectMerSettleStatictisListNew(MerSettleStatictis merSettleStatictis);

  MerSettleStatictis selectMerSettleStatictisById(Map<String, Object> map);

  Map<String, Object> selectMerSettleStatictisByMid(MerSettleStatictis merSettleStatictis);

  Integer updateMerSettleStatictisStatusById(String[] str, String status) throws SQLException;

  Integer updateMerSettleStatictisStatusById(MerSettleStatictis merSettleStatictis);

  MerSettleStatictis selectMerSettleStatictisByIdNew(MerSettleStatictis merSettleStatictis);

  MerSettleStatictis selectSubMerInfoLeftMerSettleStatictis(MerSettleStatictis merSettleStatictis);

  List<MerSettleStatictis> selectMerSettleStatictisStatusByMerSysIdNew(
      MerSettleStatictis merSettleStatictis);

  List<MerSettleStatictis> selectMerSettleStatictisByMerSysIdNew(
      MerSettleStatictis merSettleStatictis);

  Integer updateMerSettleStatictisById(MerSettleStatictis merSettleStatictis);

  /**
   * 修改清分状态（根据MID，MER_TYPE,SETTLE_DATE）
   * 
   * @param merSettleStatictis
   * @return
   * @throws Exception
   */
  Integer updateClearStatus(MerSettleStatictis merSettleStatictis) throws Exception;

  /**
   * 按每日统计成功交易的金额、手续费YJH
   * 
   * @param mapParameter
   * @return
   */
  List<MerSettleStatictis> getSucAmtStatistics(Map<String, Object> mapParameter);

  Integer insertMerSettleStatictis(MerSettleStatictis merSettleStatictis);

  /**
   * 
   * @Description: 查询所有清分,无需分页
   * @Auther: ljl
   * @param map
   * @return
   * @Date: 2014-9-19 下午3:12:27
   */
  List<MerSettleStatictis> selectMerSettleStatictisListNotPage(Map<String,Object> map);
  /**
	 * 日交易金额、手续费统计
	 * @param mapParameter
	 * @return
	 */
	List<MerSettleStatictis> dayMerAmtFee(Map mapParameter);
	/**
	 * 查询清分失败子商户
	 */
	List<MerSettleStatictis> getClearFaile(MerSettleStatictis merSettleStatictis);
}
