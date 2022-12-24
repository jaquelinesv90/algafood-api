package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import com.algaworks.algafood.domain.service.RestaurantRegisterService;
import com.algaworks.domain.exception.EntityNotFoundException;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {
	
	@Autowired
	private RestaurantRepository repository;	
	
	@Autowired
	private RestaurantRegisterService service;
	
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
	
	@PostMapping
	public ResponseEntity<?> add(@RequestBody Restaurant restaurant){
		try {
			restaurant = service.add(restaurant);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(restaurant);
		}catch(EntityNotFoundException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
}
