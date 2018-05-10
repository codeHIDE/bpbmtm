package com.bypay.domain.clientTool;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class EmptyStringAdapter extends XmlAdapter<String, String> {

	@Override
	public String unmarshal(String v) throws Exception {
		if (v == null ||"null".equalsIgnoreCase(v)) {
			return null;
		}
		
		return v;
	}

	@Override
	public String marshal(String v) throws Exception {
		if (v == null ||"null".equalsIgnoreCase(v)) {
			return null;
		}
		
		return v.trim();
	}

}
