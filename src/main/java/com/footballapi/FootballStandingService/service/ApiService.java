package com.footballapi.FootballStandingService.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.footballapi.FootballStandingService.Exceptions.ApiConsumptionException;
import com.footballapi.FootballStandingService.bean.Country;
import com.footballapi.FootballStandingService.bean.League;
import com.footballapi.FootballStandingService.bean.Standings;
import com.footballapi.FootballStandingService.constants.Constants;
import com.footballapi.FootballStandingService.dto.FootballDTO;

@Service
public class ApiService {

	private static Logger logger=LoggerFactory.getLogger(ApiService.class);
	@Autowired
	private RestTemplate restTemplate;
	
	public FootballDTO getStandingService(String country,String league,String team) throws JsonMappingException, JsonProcessingException
	{
		Set<Country> countrySet=null;
		Set<League> leagueSet=null;
		try {
		/* Get country JSON*/
		ResponseEntity<Set<Country>> countryResponse=restTemplate.exchange(Constants.API_get_countries, HttpMethod.GET,null,new ParameterizedTypeReference<Set<Country>>() {
		},Constants.API_key);
		
		countrySet=countryResponse.getBody();
		}
		catch (Exception e) {
			logger.error(e.toString());
			throw new ApiConsumptionException("Exception while consuming API "+Constants.API_get_countries+"!\n", HttpStatus.BAD_REQUEST);
		}
		System.out.println(countrySet);
		
		/* Filter out country details*/
		Country reqCountry=countrySet.stream().filter(cntry->cntry.getCountryName().equals(country)).findAny()
		.orElseThrow(()->new NoSuchElementException("Specified Country "+country+" does not exist"));
		
		try {
		/*Get League JSON*/
		ResponseEntity<Set<League>> leagueResponse=restTemplate.exchange(Constants.API_get_leagues, HttpMethod.GET,null,
				new ParameterizedTypeReference<Set<League>>() {
				},reqCountry.getCountryId(),Constants.API_key);
		
		
		leagueSet=leagueResponse.getBody();
		}
		catch (Exception e) {
			logger.error(e.toString());
			throw new ApiConsumptionException("Exception while consuming API "+Constants.API_get_leagues+"!\n", HttpStatus.BAD_REQUEST);
		}
		System.out.println(leagueSet);
		/* Filter out league details */
		League reqLeague=leagueSet.stream().filter(lgue->lgue.getLeagueName().equalsIgnoreCase(league)).findAny()
		.orElseThrow(()->new NoSuchElementException("Specified league "+league+" does not exist for country "+country));
		
		System.out.println(reqLeague);
		
		List<Standings> standingsList=null;
		try {
		/* Get Standings JSON*/
		ResponseEntity<List<Standings>> standingsRespose=restTemplate.exchange(Constants.API_get_standings, HttpMethod.GET,null,
				new ParameterizedTypeReference<List<Standings>>() {
				},reqLeague.getLeagueId(),Constants.API_key);
		
		standingsList=standingsRespose.getBody();
		}
		catch (Exception e) {
			logger.error(e.toString());
			throw new ApiConsumptionException("Exception while consuming API "+Constants.API_get_standings+"!\n", HttpStatus.BAD_REQUEST);
		}
		
		System.out.println(standingsList);
		
		Standings responseStanding=standingsList.stream().filter(standing->standing.getCountryName().equalsIgnoreCase(country)&&
				standing.getLeagueName().equalsIgnoreCase(league) && standing.getTeamName().equalsIgnoreCase(team)).findAny()
		.orElseThrow(()->new NoSuchElementException("No standings for country "+country+" league "+league+" team "+team));
		
		FootballDTO footballDTO=new FootballDTO();
		footballDTO.setCountryId(reqCountry.getCountryId());
		footballDTO.setCountryName(reqCountry.getCountryName());
		footballDTO.setLeagueId(reqLeague.getLeagueId());
		footballDTO.setLeagueName(reqLeague.getLeagueName());
		footballDTO.setTeamId(responseStanding.getTeamId());
		footballDTO.setTeamName(responseStanding.getTeamName());
		footballDTO.setStandingPosition(responseStanding.getStandingPos());
		return footballDTO;
	}
}
