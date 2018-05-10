package com.bypay.dao;

import java.util.List;
import java.util.Map;

import com.bypay.domain.ScanOrderInfo;

public interface ScanOrderInfoDao {
    public void insertInfo(ScanOrderInfo scanOrderInfo);
    
    public void updateInfo(ScanOrderInfo scanOrderInfo);
    
    public ScanOrderInfo selectInfoByOrderId(ScanOrderInfo scanOrderInfo);
    
    Integer countScanOrderInfo(Map map);
    
    List<ScanOrderInfo> selectScanOrderInfo(Map map);
    
    List<ScanOrderInfo> selectNoPayScanOrderInfo(Map map);
}
