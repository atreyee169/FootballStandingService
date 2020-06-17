package com.footballapi.FootballStandingService.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.footballapi.FootballStandingService.bean.Country;

public class CountryDesrializer extends StdDeserializer<Country>{

	Country country;
	public CountryDesrializer()
	{
		this(null);
	}
	protected CountryDesrializer(Class<?> vc) {
		super(vc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Country deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		JsonNode jsonNode=p.getCodec().readTree(p);
		String countryId=jsonNode.get("country_id").asText();
		String countryName=jsonNode.get("country_name").asText();
		country=new Country();
		country.setCountryId(countryId);
		country.setCountryName(countryName);
		return country;
	}
	

}
