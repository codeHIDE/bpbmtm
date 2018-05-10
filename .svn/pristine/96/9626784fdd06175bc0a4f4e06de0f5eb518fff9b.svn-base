/**
 * @ClassName:     Attr.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         Eason Jiang
 * @version        V1.0  
 * @Date           2015-7-6 下午1:58:11 
 */
package com.bypay.domain;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/** 
 * @ClassName: Attr 
 * @Description: TODO(征信接口对象类) 
 * @author Eason Jiang 
 * @date 2015-7-6 下午1:58:11 
 *  
 */
@XStreamAlias("AttrList")
public class Attr implements Serializable{
	
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;
	private int Id;		//唯一标识一个属性
	private int ValueType;		//1：字符串值；2：整数值
	private int IntValue;		//ValueType=2时有效
	private String StrValue;		//ValueType=1时有效，采用UTF-8编码
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	public int getValueType() {
		return ValueType;
	}
	public void setValueType(int valueType) {
		this.ValueType = valueType;
	}
	public int getIntValue() {
		return IntValue;
	}
	public void setIntValue(int intValue) {
		this.IntValue = intValue;
	}
	public String getStrValue() {
		return StrValue;
	}
	public void setStrValue(String strValue) {
		this.StrValue = strValue;
	}

	
}