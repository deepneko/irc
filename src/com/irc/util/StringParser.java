package com.irc.util;

import java.util.StringTokenizer;

/*
 * �����񃆁[�e�B���e�B
 * 
 * author: deepneko
 */
public class StringParser {

	/*
	 * str����number�Ԗڂ̗v�f��؂�o��
	 */
	public static String getElementLine(String str, int number){
		StringTokenizer st = new StringTokenizer(str);
		int count = 0;

		while(st.hasMoreTokens()){
			count++;
			if(count == number){
				return st.nextToken();
			}else{
				st.nextToken();
			}
		}
		
		return "";
	}
	
	/*
	 * str��token�œ񕪊�����
	 */
	public static String[] divide(String str, String token){
		String[] strArray = new String[2];
		int index = str.indexOf(token);

		strArray[0] = str.substring(0, index).trim();
		strArray[1] = str.substring(index+1).trim();
		
		return strArray;
	}
	
	public static void main(String args[]){
		System.out.println(System.getProperty("user.name"));
	}
}
