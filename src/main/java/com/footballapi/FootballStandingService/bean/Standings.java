package com.footballapi.FootballStandingService.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.footballapi.FootballStandingService.service.StandingDeserializer;

@JsonDeserialize(using =StandingDeserializer.class)
public class Standings {

	private String countryName;
	private String leagueId;
	private String teamId;
	private String teamName;
	private String leagueName;
	private String standingPos;
	
	
	public String getLeagueName() {
		return leagueName;
	}
	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(String leagueId) {
		this.leagueId = leagueId;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getStandingPos() {
		return standingPos;
	}
	public void setStandingPos(String standingPos) {
		this.standingPos = standingPos;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return countryName+"-"+leagueId+"-"+teamId+"-"+teamName+"-"+standingPos;
	}
	
}
