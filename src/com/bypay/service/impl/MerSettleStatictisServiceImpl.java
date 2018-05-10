package com.bypay.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bypay.dao.MerSettleStatictisDao;
import com.bypay.domain.MerSettleStatictis;
import com.bypay.service.MerSettleStatictisService;
@Service
public class MerSettleStatictisServiceImpl implements MerSettleStatictisService {

  @Inject
  private MerSettleStatictisDao merSettleStatictisDao;
  
  @Override
  public List<MerSettleStatictis> selectMerSettleStatictisListAmt(
      MerSettleStatictis merSettleStatictis) {
    return merSettleStatictisDao.selectMerSettleStatictisListAmt(merSettleStatictis);
  }

  @Override
  public Integer selectMerSettleStatictisListCount(Map<String, Object> map) {
    return merSettleStatictisDao.selectMerSettleStatictisListCount(map);
  }

  @Override
  public List<MerSettleStatictis> selectMerSettleStatictisList(Map<String, Object> map) {
    return merSettleStatictisDao.selectMerSettleStatictisList(map);
  }

  @Override
  public List<MerSettleStatictis> selectMerSettleStatictisListNew(
      MerSettleStatictis merSettleStatictis) {
    return merSettleStatictisDao.selectMerSettleStatictisListNew(merSettleStatictis);
  }

  @Override
  public MerSettleStatictis selectMerSettleStatictisById(Map<String, Object> map) {
    return merSettleStatictisDao.selectMerSettleStatictisById(map);
  }

  @Override
  public Map<String, Object> selectMerSettleStatictisByMid(MerSettleStatictis merSettleStatictis) {
    return merSettleStatictisDao.selectMerSettleStatictisByMid(merSettleStatictis);
  }

  @Override
  public Integer updateMerSettleStatictisStatusById(String[] str, String status)
      throws SQLException {
    return merSettleStatictisDao.updateMerSettleStatictisStatusById(str, status);
  }

  @Override
  public Integer updateMerSettleStatictisStatusById(MerSettleStatictis merSettleStatictis) {
    return merSettleStatictisDao.updateMerSettleStatictisStatusById(merSettleStatictis);
  }

  @Override
  public MerSettleStatictis selectMerSettleStatictisByIdNew(MerSettleStatictis merSettleStatictis) {
    return merSettleStatictisDao.selectMerSettleStatictisByIdNew(merSettleStatictis);
  }

  @Override
  public MerSettleStatictis selectSubMerInfoLeftMerSettleStatictis(
      MerSettleStatictis merSettleStatictis) {
    return merSettleStatictisDao.selectSubMerInfoLeftMerSettleStatictis(merSettleStatictis);
  }

  @Override
  public List<MerSettleStatictis> selectMerSettleStatictisStatusByMerSysIdNew(
      MerSettleStatictis merSettleStatictis) {
    return merSettleStatictisDao.selectMerSettleStatictisStatusByMerSysIdNew(merSettleStatictis);
  }

  @Override
  public List<MerSettleStatictis> selectMerSettleStatictisByMerSysIdNew(
      MerSettleStatictis merSettleStatictis) {
    return merSettleStatictisDao.selectMerSettleStatictisByMerSysIdNew(merSettleStatictis);
  }

  @Override
  public Integer updateMerSettleStatictisById(MerSettleStatictis merSettleStatictis) {
    return merSettleStatictisDao.updateMerSettleStatictisById(merSettleStatictis);
  }

  @Override
  public Integer updateClearStatus(MerSettleStatictis merSettleStatictis) throws Exception {
    return merSettleStatictisDao.updateClearStatus(merSettleStatictis);
  }

  @Override
  public List<MerSettleStatictis> getSucAmtStatistics(Map<String, Object> mapParameter) {
    return merSettleStatictisDao.getSucAmtStatistics(mapParameter);
  }

  @Override
  public Integer insertMerSettleStatictis(MerSettleStatictis merSettleStatictis) {
    return merSettleStatictisDao.insertMerSettleStatictis(merSettleStatictis);
  }

  @Override
  public List<MerSettleStatictis> selectMerSettleStatictisListNotPage(Map<String, Object> map) {
    return merSettleStatictisDao.selectMerSettleStatictisListNotPage(map);
  }

	@Override
	public List<MerSettleStatictis> seleteMerSettleStatictisReport(Map map) {
		return merSettleStatictisDao.seleteMerSettleStatictisReport(map);
	}
	
	@Override
	public Integer seleteMerSettleStatictisReportCount(Map map) {
		return merSettleStatictisDao.seleteMerSettleStatictisReportCount(map);
	}

	@Override
	public List<MerSettleStatictis> getClearFaile(
			MerSettleStatictis merSettleStatictis) {
		return merSettleStatictisDao.getClearFaile(merSettleStatictis);
	}

}
