/**
 * @ClassName:     PacketReq.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         Eason Jiang
 * @version        V1.0  
 * @Date           2015-7-6 下午3:05:10 
 */
package com.bypay.domain;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/** 
 * @ClassName: PacketReq 
 * @Description: TODO(征信系统,请求对象) 
 * @author Eason Jiang 
 * @date 2015-7-6 下午3:05:10 
 *  
 */
@XStreamAlias("ns1:PacketReq") 
public class PacketReq {
	private int ServiceCode;
	@XStreamImplicit(itemFieldName="AttrList")		//设置这个是多条对象 并非集合
	private List<Attr> AttrList;
	
	public int getServiceCode() {
		return ServiceCode;
	}
	public void setServiceCode(int serviceCode) {
		ServiceCode = serviceCode;
	}
	public List<Attr> getAttrList() {
		return AttrList;
	}
	public void setAttrList(List<Attr> attrList) {
		this.AttrList = attrList;
	}
	
	

}
