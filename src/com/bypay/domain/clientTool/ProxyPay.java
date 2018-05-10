package com.bypay.domain.clientTool;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement(name="list")
public class ProxyPay implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8199031100535781671L;
	/**
	 * 附言
	 */
	private String postScript;
	/**
	 * 姓名
	 */
	private String userName;
	/**
	 * 代发金额
	 */
	private String amt;
	/**
	 * 帐号2
	 */
	private String acccount2;
	
	/**
	 * 交易结果
	 */
	private String transtatus;
	
	/**
	 * 银行行号
	 */
	private String code;
	
	/**
	 * 明细ID
	 */
	private String preDetailId;
	
	/**
	 * 手续费
	 */
	private String transRate;

	@XmlElement(name="postScript")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getPostScript() {
		return postScript;
	}
	public void setPostScript(String postScript) {
		this.postScript = postScript;
	}
	@XmlElement(name="userName")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@XmlElement(name="amt")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	@XmlElement(name="account2")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getAcccount2() {
		return acccount2;
	}
	public void setAcccount2(String acccount2) {
		this.acccount2 = acccount2;
	}
	@XmlElement(name="transtatus")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getTranstatus() {
		return transtatus;
	}
	public void setTranstatus(String transtatus) {
		this.transtatus = transtatus;
	}
	@XmlElement(name="code")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@XmlElement(name="preDetailId")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getPreDetailId() {
		return preDetailId;
	}
	public void setPreDetailId(String preDetailId) {
		this.preDetailId = preDetailId;
	}
	@XmlElement(name="transRate")
	@XmlJavaTypeAdapter(EmptyStringAdapter.class)
	public String getTransRate() {
		return transRate;
	}
	public void setTransRate(String transRate) {
		this.transRate = transRate;
	}
	
  	
	
}
