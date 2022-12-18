package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepository;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {
	
	@Autowired
	private RestaurantRepository repository;	
	
	
	@GetMapping
	public List<Restaurant> all(){
		return repository.all();
	}
	
	@GetMapping("/{restaurantId}")
	public ResponseEntity<Restaurant> findById(@PathVariable("restaurantId") Long id){
		Restaurant restaurant = repository.findById(id);
		
		if(restaurant != null) {
			return ResponseEntity.ok(restaurant);
		}
		
		return ResponseEntity.notFound().build();
	}
	
}
