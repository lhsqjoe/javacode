package com.primemobi.iaas.util;

import java.text.ParseException;
import java.util.Date;

public class DateFormat {

	public static Date dateFormatStringUtils(String s) {
		java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd");
		Date timeDate = null;
		try {
			timeDate = format1.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return timeDate;
	}

	public static String dateFormat(Date d) {
		java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format1.format(d);
	}


	public static Date converStrToDateByFormatStr(String dateStr,String formatStr){
		java.text.DateFormat format1 = new java.text.SimpleDateFormat(formatStr);
		Date timeDate = null;
		try {
			timeDate = format1.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return timeDate;
	}

	public static String formatDateByFormatStr(Date date,String formatStr){
		java.text.DateFormat format1 = new java.text.SimpleDateFormat(formatStr);
		return format1.format(date);
	}


	public static void main(String[] args) {
		System.out.println(dateFormatStringUtils("2014-12-24"));
		Date date = converStrToDateByFormatStr("06/03/2017","MM/dd/yyyy");
		System.out.println(formatDateByFormatStr(date,"yyyy-MM-dd"));

	}

}
