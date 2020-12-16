package com.icicibank.apimgmt.util;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class TimeUtils {

	
	public static void main(String[] args) {
		
		
		
		System.out.println(ChronoUnit.SECONDS.between(LocalDateTime.now(Clock.systemUTC()), LocalDateTime.now()));
		
	}
}
