package com.bypay.domain;

public class MerInfoUpdate {
	private String id;	//	ID	BIGINT	8	否	自增长
	private String mid;	//	MID	BIGINT	8	否	商户号机构与子商户共用 
	private String merType;	//	MER_TYPE	SMALLINT	2	否	商户类型  0机构  1子商户
	private String modifyType;	//	MODIFY_TYPE	VARCHAR	2	否	更新类型  01费率变更  02支付通道变更  03清分类型变更
	private String value;	//	VALUE	VARCHAR	50	是	变更数据值
	private String status;	//	STATUS	SMALLINT	2	否	变更状态  0：审请  1：允许  2：已变更  3：不允许
	private String createTime;	//	CREATE_TIME	VARCHAR	25	是	创建时间
	private String updateTime;	//	UPDATE_TIME	VARCHAR	25	是	更新日期 YYYY-MM-DD
	private String remark;	//	REMARK	VARCHAR	50	是	备注
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMerType() {
		return merType;
	}
	public void setMerType(String merType) {
		this.merType = merType;
	}
	public String getModifyType() {
		return modifyType;
	}
	public void setModifyType(String modifyType) {
		this.modifyType = modifyType;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
