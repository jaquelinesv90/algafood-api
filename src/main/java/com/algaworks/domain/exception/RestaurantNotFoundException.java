package com.algaworks.domain.exception;


public class RestaurantNotFoundException extends EntityNotFoundException {
	
	private static final long serialVersionUID = 1L;
	
	public RestaurantNotFoundException(String message) {
		super(message);
	}
	
	public RestaurantNotFoundException(Long restaurantId) {
		this(String.format("There is no register for restaurant"
				+ " with the code %d", restaurantId));
	}

}
