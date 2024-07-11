package com.piseth.java.school.spring_webflux_demo.util;

public class SleepUtil {
	
	public static void sleepSecond(int second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
