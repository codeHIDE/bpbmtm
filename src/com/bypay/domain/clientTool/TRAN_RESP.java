package com.bypay.domain.clientTool;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("TRAN_RESP") 
public class TRAN_RESP {
	private String RESP_TYPE;
	private String RESP_CODE;
	private String RESP_MSG;
	private String COMPANY_ID;
	private String MCHNT_CD;
	private String TRAN_DATE;
	private String TRAN_TIME;
	private String TRAN_ID;
	private String BANK_TRAN_ID;
	private String BANK_TRAN_DATE;
	private String RESV;
	public String getRESP_TYPE() {
		return RESP_TYPE;
	}
	public void setRESP_TYPE(String rESP_TYPE) {
		RESP_TYPE = rESP_TYPE;
	}
	public String getRESP_CODE() {
		return RESP_CODE;
	}
	public void setRESP_CODE(String rESP_CODE) {
		RESP_CODE = rESP_CODE;
	}
	public String getRESP_MSG() {
		return RESP_MSG;
	}
	public void setRESP_MSG(String rESP_MSG) {
		RESP_MSG = rESP_MSG;
	}
	public String getCOMPANY_ID() {
		return COMPANY_ID;
	}
	public void setCOMPANY_ID(String cOMPANY_ID) {
		COMPANY_ID = cOMPANY_ID;
	}
	public String getMCHNT_CD() {
		return MCHNT_CD;
	}
	public void setMCHNT_CD(String mCHNT_CD) {
		MCHNT_CD = mCHNT_CD;
	}
	public String getTRAN_DATE() {
		return TRAN_DATE;
	}
	public void setTRAN_DATE(String tRAN_DATE) {
		TRAN_DATE = tRAN_DATE;
	}
	public String getTRAN_TIME() {
		return TRAN_TIME;
	}
	public void setTRAN_TIME(String tRAN_TIME) {
		TRAN_TIME = tRAN_TIME;
	}
	public String getTRAN_ID() {
		return TRAN_ID;
	}
	public void setTRAN_ID(String tRAN_ID) {
		TRAN_ID = tRAN_ID;
	}
	public String getBANK_TRAN_ID() {
		return BANK_TRAN_ID;
	}
	public void setBANK_TRAN_ID(String bANK_TRAN_ID) {
		BANK_TRAN_ID = bANK_TRAN_ID;
	}
	public String getBANK_TRAN_DATE() {
		return BANK_TRAN_DATE;
	}
	public void setBANK_TRAN_DATE(String bANK_TRAN_DATE) {
		BANK_TRAN_DATE = bANK_TRAN_DATE;
	}
	public String getRESV() {
		return RESV;
	}
	public void setRESV(String rESV) {
		RESV = rESV;
	}
	
	
}
