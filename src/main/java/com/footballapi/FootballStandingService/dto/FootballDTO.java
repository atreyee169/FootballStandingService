package com.footballapi.FootballStandingService.dto;

import javax.print.attribute.standard.MediaSize.Other;

public class FootballDTO {

	private String countryId;
	private String leagueId;
	private String teamId;
	private String countryName;
	private String leagueName;
	private String teamName;
	private String standingPosition;
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
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
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getLeagueName() {
		return leagueName;
	}
	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getStandingPosition() {
		return standingPosition;
	}
	public void setStandingPosition(String standingPosition) {
		this.standingPosition = standingPosition;
	}
	@Override
	public boolean equals(Object other)
	{
		if(!(other instanceof FootballDTO))
			return false;
		
		FootballDTO oth=(FootballDTO) other;
		
		return this.getCountryId().equalsIgnoreCase(oth.getCountryId())
				&& this.getCountryName().equalsIgnoreCase(oth.getCountryName())
				&& this.getLeagueId().equalsIgnoreCase(oth.getLeagueId())
				&& this.getLeagueName().equalsIgnoreCase(oth.getLeagueName())
				&& this.getStandingPosition().equalsIgnoreCase(oth.getStandingPosition())
				&& this.getTeamId().equalsIgnoreCase(oth.getTeamId())
				&& this.getTeamName().equalsIgnoreCase(oth.getTeamName());
	}
	
}
