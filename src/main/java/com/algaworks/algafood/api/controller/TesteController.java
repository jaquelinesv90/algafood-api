package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.domain.repository.RestaurantRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@GetMapping("/kitchens/by-name")
	public List<Kitchen> kitchensByName(String name){
		return kitchenRepository.findByNameContaining(name);
	}
	
	@GetMapping("/kitchens/unique-by-name")
	public Optional<Kitchen> kitchenByName(String name){
		return null; //// kitchenRepository.findByFirstname(name);
	}
	
	@GetMapping("/kitchen/exists")
	public boolean kitchenExists(String name) {
		return kitchenRepository.existsByName(name);
	}
	
	
	@GetMapping("/restaurants/by-shipping-tax")
	public List<Restaurant> restaurantByShippingTax(
			BigDecimal intialFee, BigDecimal finalFee){
		return restaurantRepository.queryByShippingTaxBetween(intialFee, finalFee);
	}
	
	@GetMapping("/restaurant/unique-by-name")
	public Optional<Restaurant> kitchenByName2(String name,Long id){
		return restaurantRepository.findByFirstname(name, id);
	}
	
	@GetMapping("/restaurant/free-shipping")
	public List<Restaurant> restaurantWithFreeShipping(String name){
				
		return restaurantRepository.findWithFreeShipping(name);
	}
	
	@GetMapping("/restaurant/first")
	public Optional<Restaurant> firstRestaurant(){
		return restaurantRepository.fetchFirst();
	}
	
}
