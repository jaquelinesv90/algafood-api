package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.repository.CityRepository;
import com.algaworks.algafood.domain.service.CityRegisterService;
import com.algaworks.domain.exception.EntityInUseException;
import com.algaworks.domain.exception.EntityNotFoundException;

@RestController
@RequestMapping(value = "/cities")
public class CityController {
	
	@Autowired
	private CityRepository repository;
	
	@Autowired
	private CityRegisterService service;
	
	@GetMapping
	public List<City> all(){
		return repository.findAll();
	}
	
	@GetMapping("/{cityId}")
	public ResponseEntity<City> findById(@PathVariable("cityId") Long id){
		Optional<City> city = repository.findById(id);
		
		if(city.isPresent()) {
			return ResponseEntity.ok(city.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public City add(@RequestBody City city) {
		return service.save(city);
	}
	
	@PutMapping("/{cityId}")
	public ResponseEntity<City> update(@PathVariable Long cityId, @RequestBody City city){
		Optional<City> currentCity = repository.findById(cityId);
		
		if(currentCity.isEmpty()) {
			BeanUtils.copyProperties(city, currentCity, "id");
			
			City savedCity = service.save(currentCity.get());
			return ResponseEntity.ok(savedCity);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{cityId}")
	public ResponseEntity<City> delete(@PathVariable Long cityId){
		try {
			service.remove(cityId);
			
			return ResponseEntity.noContent().build();
		}catch(EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
			
		}catch(EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
