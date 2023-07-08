package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.repository.CityRepository;
import com.algaworks.domain.exception.CityNotFoundException;

@Service
public class CityRegisterService {
	
	@Autowired
	private CityRepository repository;
	
	public City save(City city) {
		return repository.save(city);
	}
	
	public void remove(Long id) {
		repository.deleteById(id);
	}
	
	public City seekOrFail(Long cityId) {
		return repository.findById(cityId)
				.orElseThrow(() -> new CityNotFoundException(cityId));
	}
}
