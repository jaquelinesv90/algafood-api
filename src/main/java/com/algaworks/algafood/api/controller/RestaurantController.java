package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

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
			Restaurant currentRestaurant = repository.findById(restaurantId);	
			
			if(currentRestaurant != null) {
				// o id aqui não será copiado, pois copia de um para o outro e aí copiaria o id nulo
				BeanUtils.copyProperties(restaurant, currentRestaurant, "id");
			
				currentRestaurant = service.save(currentRestaurant);
				return ResponseEntity.ok(currentRestaurant);
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
		Restaurant currentRestaurant = repository.findById(restaurantId);
		
		if(currentRestaurant == null) {
			return ResponseEntity.notFound().build();
		}
		
		merge(fields, currentRestaurant);
		
		return update(restaurantId, currentRestaurant);
	}

	// reflectionUtils busca pra mim um campo(field) chamado nome, da classe Restaurant 
	// esse campo field vai representar o nome
	// isso é feito para atribuir o valor do field no objeto Restaurantdestino o valor da propriedade 
	private void merge(Map<String, Object> originField, Restaurant destinyRestaurant) {
		originField.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurant.class,nomePropriedade);
			
			System.out.println(nomePropriedade + "=" + valorPropriedade);
			
			ReflectionUtils.setField(field, destinyRestaurant, valorPropriedade);
		});
	}
}
