package com.hbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hbs.util.LoggerUtil;

@SpringBootApplication
public class HotelBookingManagementSystemApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(HotelBookingManagementSystemApplication.class, args);
		LoggerUtil.logInfo("RUNNING");
		
	}
}
