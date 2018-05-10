package com.bypay.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bypay.dao.RegionCodeDao;
import com.bypay.domain.RegionCode;
import com.bypay.service.RegionCodeService;

@Service("regionCodeService")
public class RegionCodeServiceImpl implements RegionCodeService {

  @Inject
  private RegionCodeDao regionCodeDao;

  // 添加对应表
  @Override
  public int insertRegionCode(RegionCode regionCode) {
    return regionCodeDao.insertRegionCode(regionCode);
  }

  @Override
  public List<RegionCode> selectRegionCodeList(Map<String, Object> map) {
    return regionCodeDao.selectRegionCodeList(map);
  }

  @Override
  public int selectRegionCodeListCount(RegionCode regionCode) {
    return regionCodeDao.selectRegionCodeListCount(regionCode);
  }

  @Override
  public int deleteRegionCode(RegionCode regionCode) {
    return regionCodeDao.deleteRegionCode(regionCode);
  }

  @Override
  public List<RegionCode> selectRegionCodeList2(RegionCode regionCode) {
    return regionCodeDao.selectRegionCodeList2(regionCode);
  }

}
