package com.irc.util;

import java.util.Calendar;

/*
 * 日時の出力形式を管理するクラス
 * 
 * author: deepneko
 */
public class Day {

	public static String getCurrentTime(){
		Calendar c = Calendar.getInstance();

		String hour = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
		if(hour.length() == 1)
			hour = "0" + hour;

		String minute = Integer.toString(c.get(Calendar.MINUTE));
		if(minute.length() == 1)
			minute = "0" + minute;

		String second = Integer.toString(c.get(Calendar.SECOND));
		if(second.length() == 1)
			second = "0" + second;
		
		return hour + ":" + minute + ":" + second;
	}
}