package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import com.algaworks.domain.exception.EntityNotFoundException;
import com.algaworks.domain.exception.RestaurantNotFoundException;

@Service
public class RestaurantRegisterService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	public Restaurant save(Restaurant restaurant) throws EntityNotFoundException {
		Long kitchenId = restaurant.getKitchen().getId();
		Kitchen kitchen = kitchenRepository.findById(kitchenId)
				.orElseThrow(() ->  new RestaurantNotFoundException(kitchenId));
		
		restaurant.setKitchen(kitchen);
		return restaurantRepository.save(restaurant);
	}
	
	public Restaurant seekOrFail(Long restaurantId) {
		return restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
	}
}
