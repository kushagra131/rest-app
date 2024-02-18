package com.vandelay.industries.restapp.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.vandelay.industries.restapp.exception.ResourceNotFoundException;

@ControllerAdvice
public class ResourceNotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String factoryNotFoundHandler(ResourceNotFoundException ex) {
		return ex.getMessage();
	}

}
