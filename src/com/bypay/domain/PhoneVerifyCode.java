package com.bypay.domain;

import java.io.Serializable;

/**
 * 
 * @Description:
 * @Author: lijialiang
 * @Date: 2014-12-17 下午12:08:07
 */
public class PhoneVerifyCode implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private Integer id;
  /*
   * 终端号
   */
  private String terminalId;
  /*
   * 手机号
   */
  private String phone;
  /*
   * 短信验证码
   */
  private String code;

  private String createTime;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTerminalId() {
    return terminalId;
  }

  public void setTerminalId(String terminalId) {
    this.terminalId = terminalId;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

}
