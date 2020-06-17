package com.footballapi.FootballStandingService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.footballapi.FootballStandingService.bean.Country;
import com.footballapi.FootballStandingService.bean.League;
import com.footballapi.FootballStandingService.bean.Standings;
import com.footballapi.FootballStandingService.constants.Constants;
import com.footballapi.FootballStandingService.dto.FootballDTO;
import com.footballapi.FootballStandingService.service.ApiService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApiService.class})
class TestApiService {

	@Autowired
	private ApiService apiService;
	@MockBean
	private RestTemplate restTemplate;
	
	Set<Country> apiResponseCountries;
	Set<League> apiResponseLeagues;
	List<Standings> apiResponseStandings;
	
	@BeforeEach
	public void init()
	{
		apiResponseCountries=new HashSet<Country>();
		
		Country c1=new Country();
		c1.setCountryId("C46");
		c1.setCountryName("France");
		
		apiResponseCountries.add(c1);
		
		Country c2=new Country();
		c2.setCountryId("C41");
		c2.setCountryName("England");
		
		apiResponseCountries.add(c2);
		
		apiResponseLeagues=new HashSet<League>();
		
		League l1=new League();
		l1.setLeagueId("L149");
		l1.setLeagueName("Championship");
		
		apiResponseLeagues.add(l1);
		
		League l2=new League();
		l2.setLeagueId("L150");
		l2.setLeagueName("France-League");
		
		apiResponseLeagues.add(l2);
		
		apiResponseStandings=new ArrayList<Standings>();
		
		Standings standings1=new Standings();
		standings1.setCountryName("France");
		standings1.setLeagueId("L150");
		standings1.setLeagueName("France-League");
		standings1.setStandingPos("14");
		standings1.setTeamId("T21");
		standings1.setTeamName("Team-Fra1");
		
		apiResponseStandings.add(standings1);
		
		Standings standings2=new Standings();
		standings2.setCountryName("France");
		standings2.setLeagueId("L150");
		standings2.setLeagueName("France-League");
		standings2.setStandingPos("1");
		standings2.setTeamId("T14");
		standings2.setTeamName("Team-Fra2");
		
		apiResponseStandings.add(standings2);
		
		Standings standings3=new Standings();
		standings3.setCountryName("England");
		standings3.setLeagueId("L149");
		standings3.setLeagueName("Championship");
		standings3.setStandingPos("12");
		standings3.setTeamId("T15");
		standings3.setTeamName("Team-Eng2");
		
		apiResponseStandings.add(standings3);
		
		when(restTemplate.exchange(Constants.API_get_countries, HttpMethod.GET,null,new ParameterizedTypeReference<Set<Country>>() {
		},Constants.API_key)).thenReturn(new ResponseEntity<Set<Country>>(apiResponseCountries,HttpStatus.OK));
		
		when(restTemplate.exchange(Mockito.eq(Constants.API_get_leagues), Mockito.eq(HttpMethod.GET),Mockito.any(),Mockito.eq(new ParameterizedTypeReference<Set<League>>() {
		}),Mockito.anyString(),Mockito.eq(Constants.API_key))).thenReturn(new ResponseEntity<Set<League>>(apiResponseLeagues,
				HttpStatus.OK));
		
	}
	@Test
	public void testGetStandingServiceSuccess() throws JsonMappingException, JsonProcessingException {
		FootballDTO expectedFootballDTO=new FootballDTO();
		expectedFootballDTO.setCountryId("C41");
		expectedFootballDTO.setCountryName("England");
		expectedFootballDTO.setLeagueId("L149");
		expectedFootballDTO.setLeagueName("Championship");
		expectedFootballDTO.setStandingPosition("12");
		expectedFootballDTO.setTeamId("T15");
		expectedFootballDTO.setTeamName("Team-Eng2");
		
		when(restTemplate.exchange(Constants.API_get_standings, HttpMethod.GET,null,new ParameterizedTypeReference<List<Standings>>() {
		},"L149",Constants.API_key)).thenReturn(new ResponseEntity<List<Standings>>(apiResponseStandings.stream()
				.filter(standing->standing.getLeagueId().equalsIgnoreCase("L149")).collect(Collectors.toList()),HttpStatus.OK));
		
		FootballDTO actualFootballDTO=apiService.getStandingService("England", "Championship", "Team-Eng2");
		
		assertEquals(expectedFootballDTO, actualFootballDTO);
	}
	
	@Test
	public void testNullCountryCodeFailure() throws JsonMappingException, JsonProcessingException
	{
		Exception ex=assertThrows(NoSuchElementException.class,()->apiService.getStandingService(null, "Championship", "Team-Eng2"));
		String actualMsg=ex.getMessage();
		String expectedMsg="Specified Country null does not exist";
		assertEquals(expectedMsg, actualMsg);
	}
	
	@Test
	public void testNoStandingsFoundFailure()
	{
		when(restTemplate.exchange(Constants.API_get_standings, HttpMethod.GET,null,new ParameterizedTypeReference<List<Standings>>() {
		},"L149",Constants.API_key)).thenReturn(new ResponseEntity<List<Standings>>(apiResponseStandings.stream()
				.filter(standing->standing.getLeagueId().equalsIgnoreCase("L149")).collect(Collectors.toList()),HttpStatus.OK));
		
		Exception ex=assertThrows(NoSuchElementException.class,()->apiService.getStandingService("England", "Championship", "Team-dummy"));
		String actualMsg=ex.getMessage();
		String expectedMsg="No standings for country England league Championship team Team-dummy";
		assertEquals(expectedMsg, actualMsg);
	}

}
