package com.algaworks.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) 
public class BusinessException extends RuntimeException{	
	
	private static final long serialVersionUID = 1L;
	
	public BusinessException(String message){
		super(message);
	}
	
	public BusinessException(String message, Throwable cause) {
		super(message,cause);
	}
}
