package com.bypay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bypay.dao.PhoneVerifyCodeDao;
import com.bypay.domain.PhoneVerifyCode;
import com.bypay.service.PhoneVerifyCodeService;

@Service("phoneVerifyCodeService")
public class PhoneVerifyCodeServiceImpl implements PhoneVerifyCodeService {
  @Autowired
  private PhoneVerifyCodeDao phoneVerifyCodeDao;

  @Override
  public Boolean insertPhoneVerifyCode(PhoneVerifyCode phoneVerifyCode) {
    return phoneVerifyCodeDao.insertPhoneVerifyCode(phoneVerifyCode);
  }
}
