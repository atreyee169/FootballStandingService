package com.footballapi.FootballStandingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.footballapi.FootballStandingService.service.ApiService;

@SpringBootApplication
public class FootballStandingServiceApplication implements CommandLineRunner{

	@Autowired
	ApiService apiService;
	public static void main(String[] args) {
		SpringApplication.run(FootballStandingServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		apiService.getStandingService("England", "Championship", "Fulham");
	}

}
