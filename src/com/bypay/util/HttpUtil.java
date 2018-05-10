package com.bypay.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

public class HttpUtil {
	
	private static final Logger logger = Logger.getLogger(HttpUtil.class);


	/**
	 * 发送get请求
	 * @param url 完全拼装好的url http://XXX?name1=value1&name2=value2
	 * @return
	 * @throws IOException 
	 */
 	public static  String sendGet(String url) throws IOException{
		String result = "";
		BufferedReader in = null;
		StringBuffer sb = new StringBuffer();
		URL requestUrl = new URL(url);
		logger.info("请求url:"+url);
		//打开连接
		URLConnection connection = requestUrl.openConnection();
		
		//设置请求属性
		connection.setRequestProperty("accept", "/");
		connection.setRequestProperty("connection", "Keep-Alive");
		connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		//设置超时时间
		connection.setReadTimeout(1000 * 20);
		
		//建立连接
		connection.connect();
		
		//读取URL的响应信息
		in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
//		
		String line = "";
		while( (line = in.readLine()) != null ){
			sb.append(line);
		}
		
		if( in != null){
			in.close();
		}
		result = sb.toString();
		logger.info("返回结果：==》"+result);
		return result;
	}
	
	
	/**
	 * 发送POST请求
	 * @param url
	 * @param param 参数 name1=value1&name2=value2
	 * @return
	 * @throws IOException 
	 */
	public static String sendPost(String url, String param) throws IOException{
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		StringBuffer sb = new StringBuffer();
		
		logger.info("请求地址："+url);
		logger.info("请求参数："+param);
		URL requestUrl = new URL(url);
		
		//打开连接
		URLConnection connection = requestUrl.openConnection();
		
		//设置请求属性
		connection.setRequestProperty("accept", "/");
		connection.setRequestProperty("connection", "Keep-Alive");
		connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		//设置超时时间
		connection.setReadTimeout(1000 * 20);
		
		//发送POST请求必须设置如下两行
		connection.setDoOutput(true);
		connection.setDoInput(true);
		
		//获取URLConnection对象对应的输出流
		out = new PrintWriter(connection.getOutputStream());
		
		//发送请求参数
		out.print(param);
		
		//flush输出流的缓冲
		out.flush();
		
		//读取输入流来读取URL的响应
		in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		
		String line = "";
		while( (line = in.readLine() ) != null ){
			sb.append(line);
		}
		
		if( out != null){
			out.close();
		}
		if( in != null ){
			in.close();
		}
		
		result = sb.toString();
		logger.info("返回结果："+result);
		return result;
	}
	
}
