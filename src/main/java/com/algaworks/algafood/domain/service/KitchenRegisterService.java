package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;

@Service
public class KitchenRegisterService {
	
	@Autowired 
	private KitchenRepository repository;
	
	public Kitchen save(Kitchen kitchen) {
		return repository.save(kitchen);
	}
	
	public void remove(Long id) {
		repository.remove(id);
	}

}
