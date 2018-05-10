package com.bypay.domain;

public class SysManager {
	
	private String id;
	
	private String loginName;	//varchar	20	否	登录名
	private String loginPwd;	//varchar	32	否	登录密码
	private String department;	//varchar	30	否	所属部门
	private String realName;	//varchar	30	否	真实姓名
	private String phone;	//varchar	20	否	联系电话
	private String email;	//varchar	30	否	邮箱
	private String level;	//varchar	2	否	级别
	private String bandIp;	//varchar	20	是	绑定ｉｐ
	private String levelPwd;	//varchar	32	是	级别确认密码
	private String purview;       //text		是	权限模块表
	private String lastLoginTime;	//varchar	20	是	最后登录时间
	private Integer loginTimes;	//int	11	否	登录次数
	private String lastLoginIp;	//varchar	20	是	最后登录ｉｐ
	private String status;	//varchar	1	否	状态0未使用1正在使用2暂停
	private String createTime;	//varchar	20	否	创建时间
	private String reserved;	//varchar	50	是	扩展
	private String actionName; //方法名
	
	private String reportEmail;	//REPORT_EMAIL 报警邮箱
	
	
	public String getReportEmail() {
		return reportEmail;
	}
	public void setReportEmail(String reportEmail) {
		this.reportEmail = reportEmail;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getBandIp() {
		return bandIp;
	}
	public void setBandIp(String bandIp) {
		this.bandIp = bandIp;
	}
	public String getLevelPwd() {
		return levelPwd;
	}
	public void setLevelPwd(String levelPwd) {
		this.levelPwd = levelPwd;
	}
	public String getPurview() {
		return purview;
	}
	public void setPurview(String purview) {
		this.purview = purview;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Integer getLoginTimes() {
		return loginTimes;
	}
	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
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
	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	
	
}
