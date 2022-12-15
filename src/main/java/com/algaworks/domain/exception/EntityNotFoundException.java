package com.algaworks.domain.exception;

public class EntityNotFoundException extends RuntimeException{	
	
	private static final long serialVersionUID = 1L;
	
	private String message = "";
	
	public EntityNotFoundException(String message){
		this.message = message;
	}
	
	public String toString() {
		return message;
	}
}
