package com.bypay.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

	public static String getPropertiesValus(String classpath, String key) {
		Properties p = new Properties();
		InputStream is = null;
		try {
			is = PropertiesUtils.class.getClassLoader().getResourceAsStream(
					classpath);
			p.load(is);
			return p.getProperty(key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * 获取com/bypayps/config/path.properties当前Key的值
	 * @param key
	 *       busUrl:前置发送到业务的url地址
	 * @return
	 */
	public static String getPropertiesValueInPath(String key) {
		Properties p = new Properties();
		InputStream is = null;
		try {
			is = PropertiesUtils.class.getClassLoader().getResourceAsStream(
					"com/bypay/config/path.properties");
			p.load(is);
			return p.getProperty(key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
