package com.footballapi.FootballStandingService.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.footballapi.FootballStandingService.service.LeagueDeserializer;

@JsonDeserialize(using = LeagueDeserializer.class)
public class League {

	private String leagueId;
	private String leagueName;
	public String getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(String leagueId) {
		this.leagueId = leagueId;
	}
	public String getLeagueName() {
		return leagueName;
	}
	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}
	
	@Override
	public String toString()
	{
		return this.leagueId+"-"+this.leagueName;
	}
	
}
