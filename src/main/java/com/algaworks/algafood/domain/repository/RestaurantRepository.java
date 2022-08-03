package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Restaurant;

public interface RestaurantRepository {
	
	List<Restaurant> all();
	Restaurant findById(Long id);
	Restaurant add(Restaurant restaurant);
	void remove(Restaurant restaurant);
}