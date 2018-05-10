package com.bypay.util;

public class IdUtil {
private static String[] key={"1", "0", "X", "9" ,"8" ,"7" ,"6" ,"5" ,"4" ,"3" ,"2"};
private static int[] key2={7, 9 ,10 ,5 ,8 ,4 ,2, 1 ,6 ,3 ,7 ,9 ,10 ,5 ,8 ,4, 2 };

public static String computeIdNo15To18(String orgIdNo){
	if(orgIdNo.length()!=15)
		return null;
	String tmpIdNo=id15To17(orgIdNo);
	if(tmpIdNo.length()!=17)
		return null;
	int count=0;
 
	for(int i=0;i<key2.length;i++){
		count=count+Integer.parseInt(""+tmpIdNo.charAt(i))*key2[i];
	}
	int index=count%11;
	tmpIdNo=tmpIdNo+key[index];
	return tmpIdNo;
}
public static String computeIdNo18To15(String orgIdNo){
	if(orgIdNo.length()!=18)
		return null;
	return orgIdNo.substring(0, 6)+orgIdNo.substring(8, 17);
	
}
public static String id15To17(String orgIdNo){
	String year=orgIdNo.substring(6, 8);
	if(Integer.parseInt(year)>=12)
		orgIdNo=orgIdNo.substring(0, 6)+"19" +orgIdNo.substring(6,15);
	else
		orgIdNo=orgIdNo.substring(0, 6)+"20" +orgIdNo.substring(6,15);
	 
	return orgIdNo;
}
public static void main(String[] args){
//	System.out.println(computeIdNo15To18("501101820321704"));
	System.out.println(computeIdNo18To15("370102198308270022"));
}
}
