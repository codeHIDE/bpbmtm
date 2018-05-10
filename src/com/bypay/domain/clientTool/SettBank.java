package com.bypay.domain.clientTool;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="bppos")
public class SettBank  extends BeanParent{
	
	
	private List<SettBankList> bankList = new ArrayList<SettBankList>();
	@XmlElement
	private String terminalId;
	
	 
	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public List<SettBankList> getBankList() {
		return bankList;
	}

	public void setBankList(List<SettBankList> bankList) {
		this.bankList = bankList;
	}

	
	
}
