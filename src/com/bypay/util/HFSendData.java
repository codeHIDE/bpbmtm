package com.bypay.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

/**
 * 
 * @Description:发送短信
 * @Author: lijialiang
 * @Date: 2014-12-24 上午10:48:03
 */
public class HFSendData {
  /**
   * 
   * @Description:发送短信
   * @Auther: lijialiang
   * @Date: 2015-1-7 下午3:44:18
   */
  public static String sendData(String phoneNo, String code) {
    // return send1(phoneNo, code);
//    return send2(phoneNo, code);
	  return null;
  }
  
  /**
   * 发送酷支付短信
   * @param phoneNo
   * @param message
   * @return
   */
  public static String sendKuZhiFu(String phoneNo, String message){
	  String sendData = "phone="+phoneNo+"&message="+message;
	  String url = "http://manager.1-town.com/RyManager/kzf/send_phone_inform.do?"+sendData;
	  String result = "";
	  try {
		result = HttpUtil.sendGet(url);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return result;
  }
  
  /**
   * 发送至尊宝短信
   * @param phoneNo
   * @param message
   * @return
   */
  public static String sendZhiZuBao(String phoneNo, String message){
	  String sendData = "phone="+phoneNo+"&message="+message;
	  String url = "http://manager.1-town.com/RyManager/send_phone_inform.do?"+sendData;
	  String result = "";
	  try {
		result = HttpUtil.sendGet(url);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return result;
  }
  
  public static void main(String[] args) {
	String phoneNo = "18626269633";
	String message = "恭喜你中了超级大奖 价值8888元大礼包";
	  sendKuZhiFu(phoneNo, message);
	  try {
		Thread.sleep(6000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	String message2 = "恭喜你中了超级大奖 价值8888元大礼包";
	sendZhiZuBao(phoneNo, message2);
}

  /**
   * 
   * @Description:调用瑞银内网短信发送接口
   * @Auther: lijialiang
   * @Date: 2014-12-24 上午11:07:03
   */
  protected static String send1(String phoneNo, String code) {
    String result = "";
    try {
      String orgId = "200006"; // 固定的6位，不可变
      String mpNo = phoneNo; // 目标手机号码
      String resv = ""; // 保留字段，不用填写
      String sendData = code;
      StringBuilder sb = new StringBuilder("I|").append(orgId).append("||").append(mpNo)
          .append('|').append(sendData).append('|').append(resv);
      System.out.println(sb.toString());
      byte[] str = writeAndReadTcp("10.98.50.141", 8819, sb.toString(), 32000);
      result = new String(str, "UTF-8");
    } catch (Exception e) {
      result = "error";
      e.printStackTrace();
    }
    return result;
  }
  

  /**
   *    * @Description:调用瑞银外网短信发送接口
   * @Auther: lijialiang
   * @Date: 2014-12-24 下午3:05:57
   */
  protected static String send2(String phoneNo, String code) {
    String result = "";
    try {
      URL url = new URL("http://www.chinapnr.com/hfsubms/clientAction!sendInfos.ac");
      URLConnection connection = url.openConnection();
      connection.setDoOutput(true);
      OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "8859_1");
      out.write("phoneNo=" + phoneNo + "&checkCode=" + code + ""); // 向页面传递数据。post的关键所在！
      out.flush();
      out.close();
      // 一旦发送成功，用以下方法就可以得到服务器的回应：
      String sCurrentLine;
      sCurrentLine = "";
      InputStream l_urlStream;
      l_urlStream = connection.getInputStream();
      // 传说中的三层包装阿！
      BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream));
      while ((sCurrentLine = l_reader.readLine()) != null) {
        result += sCurrentLine + "\r\n";
      }
    } catch (Exception e) {
      result = "error";
      e.printStackTrace();
    }
    return result;
  }

  private static byte[] writeAndReadTcp(String ip, int port, String sendData, int timeOut)
      throws Exception {
    System.out.println("[ip:" + ip + "]");
    System.out.println("[port:" + port + "]");
    System.out.println("[sendData:" + sendData + "]");
    System.out.println("[timeOut:" + String.valueOf(timeOut) + "]");
    Socket sid = null;
    DataOutputStream out = null;
    DataInputStream in = null;
    int len0 = 0, len = 0, k = 0;
    byte[] bData, bSendData, bRecvData;

    bSendData = sendData.getBytes("GBK");
    // bSendData = sendData.getBytes();

    try {
      sid = new Socket(ip, port);
      out = new DataOutputStream(sid.getOutputStream());
      in = new DataInputStream(sid.getInputStream());

      sid.setSoTimeout(timeOut);

      bData = new byte[4];
      append(bData, 0, "" + bSendData.length, 4, 'r');
      out.write(bData);
      out.write(bSendData);

      in.read(bData, 0, 4);
      len0 = (new Integer(new String(bData))).intValue();

      int j = len0;
      bRecvData = new byte[len0];
      while (true) {
        if (j <= 0) {
          break;
        }
        len = in.read(bRecvData, k, j);
        if (len < 0) {
          break;
        }

        k += len;
        j -= len;
      }

      sid.close();
      in.close();
      out.close();
    } catch (Exception e) {
      try {
        sid.close();
        in.close();
        out.close();
      } catch (IOException sube) {
        System.out.println("io err" + sube + "]");
        return null;
      }
      throw new Exception("sms send err", e);
    }
    return bRecvData;
  }

  private static void append(byte bData[], int Start, String s, int len, char mode) {

    byte bTmp[] = s.getBytes();
    int l = bTmp.length;

    byte bb = (byte) '0';
    if (mode == 'l') {
      bb = (byte) ' ';
    }

    int i;
    for (i = 0; i < len; i++)
      bData[Start + i] = bb;

    if (l > len)
      l = len;

    if (mode == 'l') {
      for (i = 0; i < l; i++)
        bData[Start + i] = bTmp[i];
    } else {
      for (i = 0; i < l; i++)
        bData[Start + len - l + i] = bTmp[i];
    }

  }
}
