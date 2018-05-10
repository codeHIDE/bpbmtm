package com.bypay.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.bypay.dao.SubMerRateDao;
import com.bypay.domain.MerInfoUpdate;
import com.bypay.domain.SubMerRate;
import com.bypay.service.MerInfoUpdateService;
import com.bypay.service.SubMerRateService;
import com.bypay.util.DateUtil;

@Service("SubMerRateService")
public class SubMerRateServiceImpl implements SubMerRateService {
  @Inject
  private SubMerRateDao subMerRateDao;
  @Inject
  private MerInfoUpdateService merInfoUpdateService;

  @Override
  public int insertSubMerRateInfo(Map<Object, Object> map) {
    return subMerRateDao.insertSubMerRateInfo(map);
  }

  @Override
  public List<SubMerRate> selectSubMerRateById(SubMerRate subMerRate) {
    return subMerRateDao.selectSubMerRateById(subMerRate);
  }

  @Override
  public SubMerRate selectSubMerRateGetById(SubMerRate sr) {
    return subMerRateDao.selectSubMerRateGetById(sr);
  }

  @Override
  public Integer updateSubMerRate(SubMerRate subMerRate) {
    return subMerRateDao.updateSubMerRate(subMerRate);
  }

  @Override
  public void updateSubMerRateRateType() {
    List<MerInfoUpdate> merInfoUpdateList = merInfoUpdateService.selectMerInfoUpdateStatus();
    SubMerRate subMerRate = null;
    Iterator<MerInfoUpdate> merInfoUpdateIt = merInfoUpdateList.iterator();
    while (merInfoUpdateIt.hasNext()) {
      MerInfoUpdate merInfoUpdate = (MerInfoUpdate) merInfoUpdateIt.next();
      subMerRate = new SubMerRate();
      String[] s = merInfoUpdate.getValue().split("\\|");
      subMerRate.setSubMerId(merInfoUpdate.getMid());
      if (merInfoUpdate.getModifyType().equals("03")) {
        subMerRate.setStatus("0");
        subMerRateDao.updateSubMerRateRateType(subMerRate);
      }
      for (int i = 0; i < s.length; i++) {
        if (!"".equals(s[i])) {
          JSONObject jsonObj = JSONObject.fromObject(s[i]);
          if (merInfoUpdate.getModifyType().equals("01")) {
            subMerRate.setRateType(jsonObj.getString("rateType"));
            subMerRate.setTeransRate(jsonObj.getString("teransRate"));
            subMerRate.setTransHighestFee(jsonObj.getString("transHighestFee"));
            subMerRate.setAgentL1Rate(jsonObj.getString("agentL1Rate"));
            subMerRate.setAgentL1ProfitRate(jsonObj.getString("agentL1ProfitRate"));
            subMerRate.setAgentL1HighestFee(jsonObj.getString("agentL1HighestFee"));
            subMerRate.setAgentL2HigestFee(jsonObj.getString("agentL2HigestFee"));
            subMerRate.setAgentL2ProfitRate(jsonObj.getString("agentL2ProfitRate"));
            subMerRate.setAgentL2Rate(jsonObj.getString("agentL2Rate"));
            subMerRateDao.updateSubMerRate(subMerRate);
          } else if (merInfoUpdate.getModifyType().equals("03")) {
            subMerRate.setStatus("1");
            subMerRate.setRateType(jsonObj.getString("rateType"));
            subMerRateDao.updateSubMerRateRateType(subMerRate);
          }
        }
        merInfoUpdate.setStatus("2");
        merInfoUpdate.setUpdateTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
        merInfoUpdateService.updateMerInfoUpdateStatus(merInfoUpdate);
      }
    }
  }

  @Override
  // 查询指定的子商户交易费率，用于子商户的详情显示
  public SubMerRate getSubMerRateBySubMerId(SubMerRate sr) {
    return subMerRateDao.getSubMerRateBySubMerId(sr);
  }

}
