package com.bypay.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
@Component("xmlUtilnew")
public class XmlUtilnew
{
	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller;

	public Object XmlToObj(String xml){
		Source source = new StreamSource(new StringReader(xml));
		Object result=jaxb2Marshaller.unmarshal(source);
		return result;
	} 
	
	public String ObjToXml(Object object) {
		StringWriter sw = new StringWriter();
		Result result = new StreamResult(sw);
		jaxb2Marshaller.marshal(object, result);
		return sw.toString();
	}

}
