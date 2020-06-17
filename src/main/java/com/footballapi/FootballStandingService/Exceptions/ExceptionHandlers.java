package com.footballapi.FootballStandingService.Exceptions;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleInvalidData(NoSuchElementException ex)
	{
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<String> handleMissingParameters(MissingServletRequestParameterException ex)
	{
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ApiConsumptionException.class)
	public ResponseEntity<String> handleMissingParameters(ApiConsumptionException ex)
	{
		return new ResponseEntity(ex.getMessage(),ex.getStatusCode());
	}
}
