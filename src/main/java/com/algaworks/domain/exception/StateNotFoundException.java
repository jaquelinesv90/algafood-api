package com.algaworks.domain.exception;

//@ResponseStatus(value = HttpStatus.NOT_FOUND) não precisa mais dessa anotação depois que extendeu a entitynotfound
public class StateNotFoundException extends EntityNotFoundException{	
	
	private static final long serialVersionUID = 1L;
	
	public StateNotFoundException(String message){
		super(message);
	}
	
	public StateNotFoundException(Long stateId) {
		this(String.format("There is no register for state with the code %d", stateId));
	}
}
