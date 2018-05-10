package com.bypay.dao;

import com.bypay.domain.PhoneVerifyCode;

/**
 * 
 * @Description:
 * @Author: lijialiang
 * @Date: 2014-12-17 下午12:18:21
 */
public interface PhoneVerifyCodeDao {

  Boolean insertPhoneVerifyCode(PhoneVerifyCode phoneVerifyCode);

  /**
   * 手机验证码验证
   * @param phoneVerifyCode
   * @return
   */
  PhoneVerifyCode selectPhoneVerifyCode(PhoneVerifyCode phoneVerifyCode);

}
