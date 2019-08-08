package com.mediaflow.tips.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class OpenStringUtils {
	
	public static String getCurrentTime() {
		Date date = new Date();
		String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		
		return dateStr;
	}
	

	public static String getCurrentTime2() {
		Date date = new Date();
		String dateStr = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
		
		return dateStr;
	}
	
	public static String getCurrentTimeOfLog() {
		Date date = new Date();
		String dateStr = new SimpleDateFormat("yyyyMMddHHmmss.SSS").format(date);
		
		return dateStr;
	}
	
	public static String getCurrentTimeHHmmss() {
		Date date = new Date();
		String dateStr = new SimpleDateFormat("HHmmss").format(date);
		
		return dateStr;
	}

	public static String getCurrentTimeFullDisplayHmmss() {
		Date date = new Date();
		String dateStr = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
		
		return dateStr;
	}

	public static String getCurrentDay() {
		Date date = new Date();
		String dateStr = new SimpleDateFormat("yyyyMMdd").format(date);
		
		return dateStr;
	}
	

	public static String getCurrentTimeHHmmssSSS() {
		Date date = new Date();
		String dateStr = new SimpleDateFormat("HHmmss.SSS").format(date);
		
		return dateStr;
	}
	
	public static String getRandomUUID() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;		
	}

}
