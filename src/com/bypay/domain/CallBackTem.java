package com.bypay.domain;

import java.io.Serializable;

public class CallBackTem implements Serializable {

  private static final long serialVersionUID = 6121962859325282306L;

  private String date;

  private String name;

  private String accountNo;

  private String amt;

  private String status;

  private String tradeDesc;

  private String bankName;

  private String use;

  private String batchId;

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAccountNo() {
    return accountNo;
  }

  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }

  public String getAmt() {
    return amt;
  }

  public void setAmt(String amt) {
    this.amt = amt;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTradeDesc() {
    return tradeDesc;
  }

  public void setTradeDesc(String tradeDesc) {
    this.tradeDesc = tradeDesc;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getUse() {
    return use;
  }

  public void setUse(String use) {
    this.use = use;
  }

  public String getBatchId() {
    return batchId;
  }

  public void setBatchId(String batchId) {
    this.batchId = batchId;
  }

}
