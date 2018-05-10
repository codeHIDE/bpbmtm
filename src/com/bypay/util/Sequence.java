package com.bypay.util;

import java.text.SimpleDateFormat;
import java.util.UUID;

public class Sequence {
	/**
	* 取得当前时间精确到毫秒 格式："yyyyMMddHHmmssSSS"，并联合5位随机数
	*/
	public synchronized static String createByTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");
		String timeIndex = formatter.format(new java.util.Date());
		int t = new java.util.Random().nextInt(999999);  
		timeIndex+=Integer.valueOf(t).toString().substring(1);
		return timeIndex;
	}	
	/**
	* 取得系统UUID
	*/
	public synchronized static String createByUuid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}	
	public static void main(String[] args) throws Exception {
		System.out.println("Sequence.createByUuid()："+ Sequence.createByUuid());
		System.out.println("Sequence.createByTime()："+ Sequence.createByTime());
	}
}
