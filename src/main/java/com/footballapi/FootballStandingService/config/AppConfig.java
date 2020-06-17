package com.footballapi.FootballStandingService.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.footballapi.FootballStandingService.service.RestTemplateResponseErrorHandler;

@Configuration
public class AppConfig {

	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate getRestTemplate(RestTemplateBuilder builder)
	{
		this.restTemplate=builder.errorHandler(new RestTemplateResponseErrorHandler()).build();
		return restTemplate;
	}
}
