package com.bypay.domain;

public class ScanOrderInfo {
    private long id;
    private String orderId;
    private String orderType;
    private String currency;
    private String txamt;
    private String respcd;
    private String respmsg;
    private String qrcode;
    private String scanCodeId;
    private String  transTime;
    private long userId;
    private String status;
    private String chcd;
    private String createTime;
    private String origOrderId;
    private String scanMerId;
    private String scanMerSign;
    
    private String loginName;
    private String subMerId;
    private String merSysId;
    
    private String notifyUrl;
    
    public String getNotifyUrl() {
        return notifyUrl;
    }
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
    public String getMerSysId() {
        return merSysId;
    }
    public void setMerSysId(String merSysId) {
        this.merSysId = merSysId;
    }
    public String getScanMerId() {
        return scanMerId;
    }
    public void setScanMerId(String scanMerId) {
        this.scanMerId = scanMerId;
    }
    public String getScanMerSign() {
        return scanMerSign;
    }
    public void setScanMerSign(String scanMerSign) {
        this.scanMerSign = scanMerSign;
    }
    public String getOrigOrderId() {
        return origOrderId;
    }
    public void setOrigOrderId(String origOrderId) {
        this.origOrderId = origOrderId;
    }
    public String getLoginName() {
        return loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getSubMerId() {
        return subMerId;
    }
    public void setSubMerId(String subMerId) {
        this.subMerId = subMerId;
    }
    public String getChcd() {
        return chcd;
    }
    public void setChcd(String chcd) {
        this.chcd = chcd;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getQrcode() {
        return qrcode;
    }
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }
    public String getScanCodeId() {
        return scanCodeId;
    }
    public void setScanCodeId(String scanCodeId) {
        this.scanCodeId = scanCodeId;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getOrderType() {
        return orderType;
    }
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getTxamt() {
        return txamt;
    }
    public void setTxamt(String txamt) {
        this.txamt = txamt;
    }
    public String getRespcd() {
        return respcd;
    }
    public void setRespcd(String respcd) {
        this.respcd = respcd;
    }
    public String getRespmsg() {
        return respmsg;
    }
    public void setRespmsg(String respmsg) {
        this.respmsg = respmsg;
    }
    public String getTransTime() {
        return transTime;
    }
    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    

}
