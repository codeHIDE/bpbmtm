package com.bypay.util;

/**
 *
 * <p>The Super Java Framework .</p>
 * <p>Description: 字符串处理类</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * @author Steven
 * @version 1.0
 */

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class StringHandler {

	
	private static Random rd = new Random();
	
	private static char[] ckeys = { '1', '2', '3', '8', '5', '6', '7', '8',
		'9', '0' };
	
	
  /**
   * 替换字符串中指定字符串
   * @param String str
   * @param String oldStr
   * @param String newStr
   * @return String
   */
        public static String replace(String str, String oldStr, String newStr) {
                StringBuffer buffer = new StringBuffer();
                int length = oldStr.length();
                int beginIndex = 0;

                if (length > 0) {
                        int index = str.indexOf(oldStr);
                        while (index >= 0) {
                                buffer.append(str.substring(beginIndex, index));
                                buffer.append(newStr);
                                beginIndex = index + length;
                                index = str.indexOf(oldStr, beginIndex);
                        }
                }
                buffer.append(str.substring(beginIndex));
                return new String(buffer);
        }

        public static String quote(String str, char quotationMarks) {
                String mark = String.valueOf(quotationMarks);
                return "'" + StringHandler.replace(str, mark, mark + mark) + "'";
        }

        /**
         * 全部替换字符串中指定字符串
         * @param String strin
         * @param String regex
         * @param String replacement
         * @return String
         */

         public static String replaceAll(String strin ,String regex,String replacement)
         {
          StringBuffer sb=new StringBuffer();
          int rbegin=0;
          int rend=strin.indexOf(regex);
          int rlen=regex.length();
          while(rend>=0)
          {
           sb.append(strin.substring(rbegin,rend));
           sb.append(replacement);
           rbegin=rend+rlen;
           rend=strin.indexOf(regex,rbegin);
          }
          sb.append(strin.substring(rbegin));
          strin=sb.toString();
          return strin;
         }

         /**
         * 拆分字符串
         * @param String strin, 源字符串
         * @param char c, 分割字符
         * @param int it,起始位置
         * @return String[]
         */
         public static String[] split(String strin,char c, int it)
            {
                ArrayList arraylist = new ArrayList();
          char[] chark=strin.toCharArray();
          StringBuffer sb=new StringBuffer();
          for(int i=it;i<chark.length;i++)
          {
           if(chark[i]==c)
           {
            arraylist.add(sb.toString());
            sb=new StringBuffer();
           }else{
            sb.append(chark[i]);
            if(i==chark.length-1)
            {
             arraylist.add(sb.toString());
            }
           }
          }

          int k=arraylist.size();
          String as[]=new String[k];
                return (String[])arraylist.subList(0,k).toArray(as);
            }

         /**
         * 分割字符串
         * @param char c,
         * @return String[],
         */
            public String[] split(String s,char c)
            {
                return split(s,c, 0);
            }

         /**
         * 分割字符串
         * @param String c, 分割符
         * @param String[],
         */
         public String[] split(String strin,String c)
         {
          if (strin == null)
          {
           return null;
          }

          ArrayList arraylist = new ArrayList();
          int begin = 0;
          int end = 0;
          while ((begin = strin.indexOf(c, end)) != -1)
          {
           String s2 = strin.substring(end, begin);
           //if ( s2.trim().length() > 0 ) { // ���˵�ո��
            arraylist.add(strin.substring(end, begin));
           //}
           end = begin + c.length();
          }
          if (end != strin.length())
          {
           arraylist.add(strin.substring(end));
          }

          int k=arraylist.size();
          String as[]=new String[k];
          System.out.println( "�ָ��� : " + as.length );
                return (String[])arraylist.subList(0,k).toArray(as);
         }

         /**
         * 转换字符到二进制
         * @param char c,
         * @param String,
         */
         public static String toBin(char c) {
          int k = 0x8000;
          StringBuffer sb = new StringBuffer(16);
          for (int i = 0; i < 16; k>>>=1,i++)
          {
           sb.append(((c&k) != 0)?1:0);
          }
          return sb.toString();
         }

         /**
         * 判断字符是否为字母
         * @param char c,
         * @return boolean,
         */
         public static boolean isLetter(char c) {
          int k = 0x80;
          return c/k == 0?true:false;
         }

         /**
         * 判断字符串的实际字节长度，汉字为2字节
         * @param String s
         * @return int
         */
         public static int length(String s) {
          char[] c = s.toCharArray();
          int len = 0;
          for (int i = 0; i < c.length; i++)
          {
           len++;
           if (!isLetter(c[i]))
           {
            len++;
           }
          }
          return len;
         }

         /**
         * 截取字符串，长度为字节长度
         * @param String origin,
         * @param int len,
         * @return String,
         */
         public static String substring(String origin, int len) {
          return substring(origin, 0, len);
         }

         /**
         * 截取字符串，长度为字节长度
         * @param  String origin,
         * @param  int begin,
         * @param  int len
         * @return String
         */
         public static String substring(String origin, int begin, int len) {
          if (origin == null)
          {
           return origin;
          }
          int sBegin = (begin < 0)?0:begin;
          // Խ��Χ����
          if (len < 1 || sBegin > origin.length())
          {
           return "";
          }

          if (len + sBegin > origin.length())
          {
           return origin.substring(sBegin);
          }
          char[] c = origin.toCharArray();
          StringBuffer sb = new StringBuffer();
          for (int i = sBegin,j = sBegin; i < (sBegin + 2*len); i++,j++)
          {
           if (j >= c.length) break;

           sb.append(c[j]);
           if (!isLetter(c[j]))
           {
            i++;
           }
          }
          return sb.toString();
         }


         /**
         * 转换HTML标记
         * @param String html,
         * @return String,
         */
         public static String htmlToWeb( String html ) {
          if (html == null || html.length() == 0)
          {
           return "";
          }
          char[] c = html.toCharArray();
          StringBuffer sb = new StringBuffer();
          for (int i = 0; i < c.length; i++)
          {
           switch (c[i])
           {
           case '>':
            sb.append("&gt;");
            break;
           case '<':
            sb.append("&lt;");
            break;
           case ' ':
            sb.append("&nbsp;");
            break;
           case '"':
            sb.append("&quot;");
            break;
           case '&':
            sb.append("&amp;");
            break;
           case '\n':
            sb.append("<br>");
            break;
           default:
            sb.append(c[i]);
           }
          }
          return sb.toString();
         }

         /**
     	 * 
     	 * @param n
     	 * @return
     	 */
     	public static String getRandomKeys(int n) {
     		StringBuffer sb = new StringBuffer();
     		for (int i = 0; i < n; i++)
     			sb.append(ckeys[rd.nextInt(ckeys.length)]);
     		return sb.toString();
     	}

}
