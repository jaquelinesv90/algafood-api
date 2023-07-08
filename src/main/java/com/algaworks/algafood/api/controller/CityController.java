package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.algaworks.domain.exception.BusinessException;
import com.algaworks.domain.exception.StateNotFoundException;

// classe responsavel pelo resposta HTTP, corpo, status
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
	public City findById(@PathVariable("cityId") Long id){
		return service.seekOrFail(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public City add(@RequestBody City city) {
		try {
			return service.save(city);
		} catch (StateNotFoundException e) {
			throw new BusinessException(e.getMessage());
		}
		
	}
	
	@PutMapping("/{cityId}")
	public City update(@PathVariable Long cityId, @RequestBody City city){
		try {
			
			City currentCity = service.seekOrFail(cityId);
			
			BeanUtils.copyProperties(city, currentCity,"id");
			
			return service.save(city);
			
		}catch(StateNotFoundException e){
			throw new BusinessException(e.getMessage());
		}
	}
	
	@DeleteMapping("/{cityId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long cityId) {
		service.remove(cityId);
	}
}
