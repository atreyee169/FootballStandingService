package com.footballapi.FootballStandingService.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.footballapi.FootballStandingService.bean.League;

public class LeagueDeserializer extends StdDeserializer<League>{

	League league;
	public LeagueDeserializer()
	{
		this(null);
	}
	protected LeagueDeserializer(Class<?> vc) {
		super(vc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public League deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		JsonNode jsonNode=p.getCodec().readTree(p);
		String leagueId=jsonNode.get("league_id").asText();
		String leagueName=jsonNode.get("league_name").asText();
		league=new League();
		league.setLeagueId(leagueId);
		league.setLeagueName(leagueName);
		
		return league;
	}

}
