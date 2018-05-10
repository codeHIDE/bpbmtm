package com.bypay.domain;

/**
 * 终端批次表
 * @author admin
 *
 */
public class TerminalBatchInfo {

	private String tbId;
	private String merId;
	private String batchId;
	
	public String getTbId() {
		return tbId;
	}

	public void setTbId(String tbId) {
		this.tbId = tbId;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	
}
