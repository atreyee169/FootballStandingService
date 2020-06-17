package com.footballapi.FootballStandingService.controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.footballapi.FootballStandingService.dto.FootballDTO;
import com.footballapi.FootballStandingService.service.ApiService;

@RestController
@RequestMapping("/footballservice")
public class ApiController {
	@Autowired
	ApiService apiService;

	@GetMapping
	public FootballDTO getStandingConntroller(FootballDTO footballDTO,@RequestParam
			 @Pattern(regexp = "get_standing") String action,@RequestParam @NotBlank String country,@RequestParam @NotBlank String league,@RequestParam 
			@NotBlank String team) throws JsonMappingException, JsonProcessingException
	{
		footballDTO=apiService.getStandingService(country, league, team);
		return footballDTO;
	}
}
