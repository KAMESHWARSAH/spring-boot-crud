package com.example.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResorsseNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ResorsseNotFoundException(String message) {
		super("----------------"+message);
	}
	
}
