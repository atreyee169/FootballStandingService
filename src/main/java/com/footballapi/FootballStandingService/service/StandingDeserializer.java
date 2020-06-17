package com.footballapi.FootballStandingService.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.footballapi.FootballStandingService.bean.Standings;

public class StandingDeserializer extends StdDeserializer<Standings>{

	Standings standings;
	public StandingDeserializer()
	{
		this(null);
	}
	protected StandingDeserializer(Class<?> vc) {
		super(vc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Standings deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		JsonNode jsonNode=p.getCodec().readTree(p);
		
		standings=new Standings();
		
		standings.setCountryName(jsonNode.get("country_name").asText());
		standings.setLeagueId(jsonNode.get("league_id").asText());
		standings.setTeamId(jsonNode.get("team_id").asText());
		standings.setTeamName(jsonNode.get("team_name").asText());
		standings.setLeagueName(jsonNode.get("league_name").asText());
		standings.setStandingPos(jsonNode.get("overall_league_position").asText());
		return standings;
	}

}
