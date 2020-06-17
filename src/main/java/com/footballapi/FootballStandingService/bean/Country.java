package com.footballapi.FootballStandingService.bean;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.footballapi.FootballStandingService.service.CountryDesrializer;

@Component
@JsonDeserialize(using =  CountryDesrializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
	private String countryId;
	private String countryName;
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	@Override
	public String toString()
	{
		return this.countryId+"-"+this.countryName;
	}
}
