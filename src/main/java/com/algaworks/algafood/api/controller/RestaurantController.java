package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import com.algaworks.algafood.domain.service.RestaurantRegisterService;
import com.algaworks.domain.exception.EntityNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {
	
	@Autowired
	private RestaurantRepository repository;	
	
	@Autowired
	private RestaurantRegisterService service;
	
	@GetMapping
	public List<Restaurant> all(){
		return repository.findAll();
	}
	
	@GetMapping("/{restaurantId}")
	public ResponseEntity<Restaurant> findById(@PathVariable("restaurantId") Long id){
		Optional<Restaurant> restaurant = repository.findById(id);
		
		if(restaurant.isPresent()) {
			return ResponseEntity.ok(restaurant.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> add(@RequestBody Restaurant restaurant){
		try {
			restaurant = service.save(restaurant);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(restaurant);
		}catch(EntityNotFoundException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	@PutMapping("/{restaurantId}")
	public ResponseEntity<?> update(@PathVariable Long restaurantId, @RequestBody Restaurant restaurant){
		try {
			Optional<Restaurant> currentRestaurant = repository.findById(restaurantId);	
			
			if(currentRestaurant != null) {
				// o id aqui não será copiado, pois copia de um para o outro e aí copiaria o id nulo
				BeanUtils.copyProperties(restaurant, currentRestaurant,
						"id", "listPaymentWay", "address","registerDate");
			
				Restaurant savedRestaurant = service.save(currentRestaurant.get());
				return ResponseEntity.ok(savedRestaurant);
			}
			
			return ResponseEntity.notFound().build();
			
		}catch(EntityNotFoundException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	@PatchMapping("/{restaurantId}")
	public ResponseEntity<?> partialUpdate(@PathVariable Long restaurantId, 
			@RequestBody Map<String, Object> fields){
		Optional<Restaurant> currentRestaurant = repository.findById(restaurantId);
		
		if(currentRestaurant.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		merge(fields, currentRestaurant.get());
		
		return update(restaurantId, currentRestaurant.get());
	}

	// reflectionUtils busca pra mim um campo(field) chamado nome, da classe Restaurant 
	// esse campo field vai representar o nome
	// isso é feito para atribuir o valor do field no objeto Restaurant destino o valor da propriedade 
	private void merge(Map<String, Object> originField, Restaurant destinyRestaurant) {
		
		//essa conversão é feita pois, ao digitar um numero gerava erro de Integer to Bigdecimal na taxa frete
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurant originRestaurant = objectMapper.convertValue(originField, Restaurant.class);
		
		
		originField.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurant.class,nomePropriedade);
			field.setAccessible(true);
			
			Object newValue = ReflectionUtils.getField(field, originRestaurant);
			
			System.out.println(nomePropriedade + "=" + valorPropriedade + " = " + newValue);
			
			ReflectionUtils.setField(field, destinyRestaurant, valorPropriedade);
		});
	}
}
