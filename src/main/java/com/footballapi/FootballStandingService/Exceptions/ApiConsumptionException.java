package com.footballapi.FootballStandingService.Exceptions;

import org.springframework.http.HttpStatus;

public class ApiConsumptionException extends RuntimeException{

	private HttpStatus statusCode;
	public ApiConsumptionException(String errorMsg,HttpStatus statusCode)
	{
		super("Error while consuming API!\n"+errorMsg);
		this.statusCode=statusCode;
	}
	public HttpStatus getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
	
}
