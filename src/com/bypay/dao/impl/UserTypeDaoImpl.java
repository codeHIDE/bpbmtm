package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.UserTypeDao;
import com.bypay.domain.UserType;

@Repository("userTypeDao")
public class UserTypeDaoImpl implements UserTypeDao {

@Inject
  private SqlSessionTemplate sqlSessionTemplate;

@Override
public Integer selectUserTypeCount(Map map) {
    int num = 0;
    List<Integer> l = sqlSessionTemplate.selectList("selectUserTypeCount", map);
    for (int i = 0; i < l.size(); i++) {
      num = num + l.get(i);
    }
    return num;
  }

@Override
public List<UserType> selectUserType(Map map) {
    return (List<UserType>) sqlSessionTemplate.selectList("selectUserType", map);
    }

@Override
public int insertUserType(UserType userType) {
	 return sqlSessionTemplate.insert("insertUserType", userType);
}



}
