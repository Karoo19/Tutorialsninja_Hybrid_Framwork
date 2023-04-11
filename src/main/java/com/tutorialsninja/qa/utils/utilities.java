package com.tutorialsninja.qa.utils;

import java.util.Date;

public class utilities {
	
	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGE_WAIT_TIME=5;
	public static final int SCRIPT_WAIT_TIME=100;

	
	public static String generateEmailWithTimeStamp() {
		Date date = new Date();
		String timestamp = date.toString().replace(" ","_").replace(":","_");
		return "karima10" + timestamp + "@gmail.com";
	}
}
