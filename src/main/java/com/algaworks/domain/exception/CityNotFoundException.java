package com.algaworks.domain.exception;

//@ResponseStatus(value = HttpStatus.NOT_FOUND) não precisa mais dessa anotação depois que extendeu a entitynotfound
public class CityNotFoundException extends EntityNotFoundException{	
	
	private static final long serialVersionUID = 1L;
	
	public CityNotFoundException(String message){
		super(message);
	}
	
	public CityNotFoundException(Long cityId) {
		this(String.format("There is no register for city with the code %d", cityId));
	}
}
