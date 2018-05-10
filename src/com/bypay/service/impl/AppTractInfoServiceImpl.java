package com.bypay.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.bypay.dao.AppTractInfoDao;
import com.bypay.domain.AppTractInfo;
import com.bypay.service.AppTractInfoService;
import com.bypay.util.AmountUtils;

@Service("appTractInfoService")
public class AppTractInfoServiceImpl implements AppTractInfoService {

  @Inject
  private AppTractInfoDao appTractInfoDao;

  /**
   * 添加应用通道
   */
  @Override
  public int insertAppTractInfo(AppTractInfo appTractInfo) {
    if (null != appTractInfo) {
      if (StringUtils.isNotBlank(appTractInfo.getTransLowestCost())) {
        appTractInfo.setTransLowestCost(AmountUtils.changeY2F(appTractInfo.getTransLowestCost()));
      }
      if (StringUtils.isNotBlank(appTractInfo.getTransHighestCost())) {
        appTractInfo.setTransHighestCost(AmountUtils.changeY2F(appTractInfo.getTransHighestCost()));
      }
      if (StringUtils.isNotBlank(appTractInfo.getPerCardQuota())) {
        appTractInfo.setPerCardQuota(AmountUtils.changeY2F(appTractInfo.getPerCardQuota()));
      }
      if (StringUtils.isNotBlank(appTractInfo.getCardQuota())) {
        appTractInfo.setCardQuota(AmountUtils.changeY2F(appTractInfo.getCardQuota()));
      }
    }
    return appTractInfoDao.insertAppTractInfo(appTractInfo);
  }

  // 查询添加时候的通道号是否存在
  public AppTractInfo selectAppTractInfoRepeat(String transMerId) {
    return appTractInfoDao.selectAppTractInfoRepeat(transMerId);
  }

  // 通道查询 查询所有通道信息
  public List<AppTractInfo> selectAppTractInfoList(Map map) {
    List<AppTractInfo> list = null;
    try {
      list = appTractInfoDao.selectAppTractInfoList(map);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // 查询通道的条数 分页用
  public int selectAppTractInfoCount(AppTractInfo appTractInfo) {
    return appTractInfoDao.selectAppTractInfoCount(appTractInfo);
  }

  // 通道详情
  public AppTractInfo selectAppTractById(String tractId) {
    AppTractInfo appTractInfo = appTractInfoDao.selectAppTractById(tractId);
    try {
      if (null != appTractInfo) {
        if (StringUtils.isNotBlank(appTractInfo.getTransLowestCost())) {
          appTractInfo.setTransLowestCost(AmountUtils.changeF2Y(appTractInfo.getTransLowestCost()));
        }
        if (StringUtils.isNotBlank(appTractInfo.getTransHighestCost())) {
          appTractInfo.setTransHighestCost(AmountUtils.changeF2Y(appTractInfo.getTransHighestCost()));
        }
        if (StringUtils.isNotBlank(appTractInfo.getPerCardQuota())) {
          appTractInfo.setPerCardQuota(AmountUtils.changeF2Y(appTractInfo.getPerCardQuota()));
        }
        if (StringUtils.isNotBlank(appTractInfo.getCardQuota())) {
          appTractInfo.setCardQuota(AmountUtils.changeF2Y(appTractInfo.getCardQuota()));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return appTractInfoDao.selectAppTractById(tractId);
  }

  // 通道审批更新
  public int updateAppTractInfo(AppTractInfo appTractInfo) {
    return appTractInfoDao.updateAppTractInfo(appTractInfo);
  }

  // 修改通道信息
  public int updateAppTract(AppTractInfo appTractInfo) {
    if (null != appTractInfo) {
      if (StringUtils.isNotBlank(appTractInfo.getTransLowestCost())) {
        appTractInfo.setTransLowestCost(AmountUtils.changeY2F(appTractInfo.getTransLowestCost()));
      }
      if (StringUtils.isNotBlank(appTractInfo.getTransHighestCost())) {
        appTractInfo.setTransHighestCost(AmountUtils.changeY2F(appTractInfo.getTransHighestCost()));
      }
      if (StringUtils.isNotBlank(appTractInfo.getPerCardQuota())) {
        appTractInfo.setPerCardQuota(AmountUtils.changeY2F(appTractInfo.getPerCardQuota()));
      }
      if (StringUtils.isNotBlank(appTractInfo.getCardQuota())) {
        appTractInfo.setCardQuota(AmountUtils.changeY2F(appTractInfo.getCardQuota()));
      }
    }
    return appTractInfoDao.updateAppTract(appTractInfo);
  }

  // 通道 暂停
  public int updateAppTractInfoOFF(AppTractInfo appTractInfo) {
    return appTractInfoDao.updateAppTractInfoOFF(appTractInfo);
  }

}
