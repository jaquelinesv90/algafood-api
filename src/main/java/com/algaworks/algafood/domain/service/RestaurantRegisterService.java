package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import com.algaworks.domain.exception.EntityNotFoundException;

@Service
public class RestaurantRegisterService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	public Restaurant add(Restaurant restaurant) throws EntityNotFoundException {
		Long kitchenId = restaurant.getKitchen().getId();
		Kitchen kitchen = kitchenRepository.findById(kitchenId);
		
		if(kitchen == null) {
			throw new EntityNotFoundException(
					String.format("It doesn't exist kitchen with id %d", kitchenId));
		}
		
		return restaurantRepository.add(restaurant);
	}
}
