package com.bypay.dao;

import java.util.List;
import java.util.Map;

import com.bypay.domain.MerInfo;
import com.bypay.domain.PlatMerInfo;

public interface MerInfoDao {
  Integer insertMerInfo(MerInfo merInfo);

  List<MerInfo> selectMerInfoAll(Map map);

  Integer selectMerInfoCount(MerInfo merInfo);

  MerInfo selectMerInfoById(MerInfo merInfo);

  Integer approvalMerchantInfo(MerInfo merInfo);

  Integer updateMerchantInfo(MerInfo merInfo);

  Integer updateSettleMerchantInfo(MerInfo merInfo);

  List<MerInfo> selectMerInfoAllByPlatMerId(Map map);

  Integer selectMerInfoAllByPlatMerIdCount(Map map);

  /**
   * 传图片成功后修改数据库机构LOGO字段地址
   * 
   * @param merchantInfo
   */
  int updateMerLogo(MerInfo merInfo);

  // 查询所有的 机构商户号
  List<MerInfo> selectMerInfoByAllSysId(MerInfo merInfo);

  MerInfo selectMerInfoByMerSysId(MerInfo merInfo);

  /**
   * 
   * @Description:开启暂停进件
   * @Auther: lijialiang
   * @Date: 2015-1-4 上午10:56:08
   */
  Integer updateIsIntoPieces(MerInfo merInfo);

}
