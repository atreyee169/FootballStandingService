package com.footballapi.FootballStandingService;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.footballapi.FootballStandingService.controller.ApiController;
import com.footballapi.FootballStandingService.dto.FootballDTO;
import com.footballapi.FootballStandingService.service.ApiService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {ApiController.class})
public class TestApiController {

	@Autowired
	ApiController apiController;
	@MockBean
	ApiService apiService;
	@Autowired
	MockMvc mockmvc;
	
	@BeforeEach
	public void init() throws JsonMappingException, JsonProcessingException
	{
		FootballDTO footballDTO=new FootballDTO();
		footballDTO.setCountryId("C41");
		footballDTO.setCountryName("England");
		footballDTO.setLeagueId("L149");
		footballDTO.setLeagueName("Championship");
		footballDTO.setStandingPosition("12");
		footballDTO.setTeamId("T15");
		footballDTO.setTeamName("Team-Eng2");
		
		when(apiService.getStandingService(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(footballDTO);
	}
	
	@Test
	public void testRequestSuccess() throws Exception{
		mockmvc.perform(get("/footballservice?action=get_standing&country=England&league=Championship&team=Team-Eng2")).andExpect(status().isOk())
		.andExpect(jsonPath("$.countryId", is("C41")))
		.andExpect(jsonPath("$.teamId", is("T15")))
		.andExpect(jsonPath("$.standingPosition", is("12")));
	}
	
	@Test
	public void testNullCountryRequestParamThrowsException() throws Exception
	{
		mockmvc.perform(get("/footballservice?action=get_standing&league=Championship&team=Team-Eng2")).andExpect(status().isBadRequest());
	}
	
	
}
