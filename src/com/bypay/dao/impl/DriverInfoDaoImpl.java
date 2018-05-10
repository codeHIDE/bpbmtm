package com.bypay.dao.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.DriverInfoDao;
import com.bypay.domain.DriverInfo;

@Repository("driverInfoDao")
public class DriverInfoDaoImpl implements DriverInfoDao {
    @Inject
    private SqlSessionTemplate sqlSessionTemplate;
    
    @Override
    public int insertDriverInfo(DriverInfo driverInfo) {
       return sqlSessionTemplate.insert("com.bypay.dao.DriverInfoDao.insertInfo",driverInfo);
    }

}
