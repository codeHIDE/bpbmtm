package com.bypay.dao.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.PhoneVerifyCodeDao;
import com.bypay.domain.PhoneVerifyCode;

@Repository("phoneVerifyCodeDao")
public class PhoneVerifyCodeDaoImpl implements PhoneVerifyCodeDao {
  @Inject
  private SqlSessionTemplate sqlSessionTemplate;

  @Override
  public Boolean insertPhoneVerifyCode(PhoneVerifyCode phoneVerifyCode) {
    if (sqlSessionTemplate.insert("insertPhoneVerifyCode", phoneVerifyCode) > 0)
      return true;
    else
      return false;
  }

	@Override
	public PhoneVerifyCode selectPhoneVerifyCode(PhoneVerifyCode phoneVerifyCode) {
		return (PhoneVerifyCode) sqlSessionTemplate.selectOne("selectPhoneVerifyCode", phoneVerifyCode);
	}
}
