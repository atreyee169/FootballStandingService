package com.footballapi.FootballStandingService.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import com.footballapi.FootballStandingService.Exceptions.ApiConsumptionException;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler{

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		// TODO Auto-generated method stub
		return response.getStatusCode().series()==Series.CLIENT_ERROR || response.getStatusCode().series()==Series.SERVER_ERROR;
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		// TODO Auto-generated method stub
		throw new ApiConsumptionException(new BufferedReader(new InputStreamReader(response.getBody())).lines().collect(Collectors.joining("\n")),response.getStatusCode());		
	}

}
