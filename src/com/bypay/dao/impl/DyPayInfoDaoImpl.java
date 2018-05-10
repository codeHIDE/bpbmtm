package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.DyPayInfoDao;
import com.bypay.domain.DyPayInfo;

@Repository("dyPayInfoDao")
public class DyPayInfoDaoImpl implements DyPayInfoDao{
    @Inject
    private SqlSessionTemplate sqlSessionTemplate;
    @Override
    public void insertInfo(DyPayInfo dyPayInfo) {
        sqlSessionTemplate.insert("com.bypay.dao.DyPayInfoDao.insertInfo",dyPayInfo);
    }

    @Override
    public void updateInfo(DyPayInfo dyPayInfo) {
        sqlSessionTemplate.update("com.bypay.dao.DyPayInfoDao.updateInfo",dyPayInfo);
    }

    @Override
    public void updateInfoById(DyPayInfo dyPayInfo) {
        sqlSessionTemplate.update("com.bypay.dao.DyPayInfoDao.updateInfoById",dyPayInfo);
    }
    
    @Override
    public List<DyPayInfo> selectDyPayInfoInfo(Map map) {
        return sqlSessionTemplate.selectList("com.bypay.dao.DyPayInfoDao.selectDyPayInfoInfo",map);
    }

    @Override
    public Integer selectDyPayInfoInfoCount(Map map) {
        return (Integer) sqlSessionTemplate.selectOne("com.bypay.dao.DyPayInfoDao.selectDyPayInfoInfoCount",map);
    }

    @Override
    public DyPayInfo selectInfoById(DyPayInfo dyPayInfo) {
        return (DyPayInfo) sqlSessionTemplate.selectOne("com.bypay.dao.DyPayInfoDao.selectInfoById",dyPayInfo);
    }

    @Override
    public DyPayInfo selectInfoByOrderId(DyPayInfo dyPayInfo) {
        return (DyPayInfo) sqlSessionTemplate.selectOne("com.bypay.dao.DyPayInfoDao.selectInfoByOrderId",dyPayInfo);
    }
    
}
