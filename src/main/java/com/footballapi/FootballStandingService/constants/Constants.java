package com.footballapi.FootballStandingService.constants;

public final class Constants {
	public static final String API_get_countries="https://apiv2.apifootball.com/?action=get_countries&APIkey={apikey}";
	public static final String API_get_leagues="https://apiv2.apifootball.com/?action=get_leagues&country_id={countryId}&APIkey={apikey}";
	public static final String API_get_teams="https://apiv2.apifootball.com/?action=get_teams&league_id={leagueId}&APIkey={apikey}";
	public static final String API_get_standings="https://apiv2.apifootball.com/?action=get_standings&league_id={leagueId}&APIkey={apikey}";
	public static final String API_key="9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978";

}
