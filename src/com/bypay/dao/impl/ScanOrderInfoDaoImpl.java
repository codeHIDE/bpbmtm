package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.ScanOrderInfoDao;
import com.bypay.domain.ScanOrderInfo;
import com.bypay.domain.WaresSpecInfo;

@Repository("scanOrderInfoDao")
public class ScanOrderInfoDaoImpl implements ScanOrderInfoDao{
    @Inject
    private SqlSessionTemplate sqlSessionTemplate;
    @Override
    public void insertInfo(ScanOrderInfo scanOrderInfo) {
        sqlSessionTemplate.insert("com.bypay.dao.ScanOrderInfoDao.insertInfo",scanOrderInfo);
    }

    @Override
    public void updateInfo(ScanOrderInfo scanOrderInfo) {
        sqlSessionTemplate.update("com.bypay.dao.ScanOrderInfoDao.updateInfo",scanOrderInfo);
    }

    @Override
    public ScanOrderInfo selectInfoByOrderId(ScanOrderInfo scanOrderInfo) {
        return (ScanOrderInfo) sqlSessionTemplate.selectOne("com.bypay.dao.ScanOrderInfoDao.selectInfoByOrderId",scanOrderInfo);
    }

    @Override
    public Integer countScanOrderInfo(Map map) {
        int num = 0;
        List<Integer> l = sqlSessionTemplate.selectList("com.bypay.dao.ScanOrderInfoDao.countScanOrderInfo", map);
        for (int i = 0; i < l.size(); i++) {
          num = num + l.get(i);
        }
        return num;
    }

    @Override
    public List<ScanOrderInfo> selectScanOrderInfo(Map map) {
        return (List<ScanOrderInfo>) sqlSessionTemplate.selectList("com.bypay.dao.ScanOrderInfoDao.selectScanOrderInfo", map);
    }

    @Override
    public List<ScanOrderInfo> selectNoPayScanOrderInfo(Map map) {
        return (List<ScanOrderInfo>) sqlSessionTemplate.selectList("com.bypay.dao.ScanOrderInfoDao.selectNoPayScanOrderInfo", map);
    }
    
}
