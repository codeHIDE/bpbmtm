package com.bypay.service;

import java.util.List;
import java.util.Map;
import com.bypay.domain.RegionCode;

public interface RegionCodeService {

  // 添加对应表
  int insertRegionCode(RegionCode regionCode);

  // 查询对应码表
  List<RegionCode> selectRegionCodeList(Map<String, Object> map);

  // 查询对应码表的条数 分页用
  int selectRegionCodeListCount(RegionCode regionCode);

  // 删除样式
  int deleteRegionCode(RegionCode regionCode);

  // 查询对应码表
  List<RegionCode> selectRegionCodeList2(RegionCode regionCode);

}
