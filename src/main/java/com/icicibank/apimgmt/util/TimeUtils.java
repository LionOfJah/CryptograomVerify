package com.icicibank.apimgmt.util;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class TimeUtils {

	
	public static void main(String[] args) {
		
		LocalDateTime ldt = Instant.ofEpochMilli(1609922181883l).atZone(ZoneId.systemDefault()).toLocalDateTime();
		System.out.println(ldt);
		//System.out.println(ChronoUnit.SECONDS.between(LocalDateTime.now(Clock.systemUTC()), LocalDateTime.now()));
		
	}
}
