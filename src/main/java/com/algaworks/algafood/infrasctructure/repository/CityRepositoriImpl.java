package com.algaworks.algafood.infrasctructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.repository.CityRepository;

@Component
public class CityRepositoriImpl implements CityRepository{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<City> all() {
		return manager.createQuery("from City", City.class).getResultList();
	}

	@Override
	public City findById(Long id) {
		return manager.find(City.class, id);
	}

	@Override
	@Transactional
	public City save(City city) {
		return manager.merge(city);
	}

	@Override
	@Transactional
	public void remove(City city) {
		city = findById(city.getId());
		manager.remove(city);
	}
}
