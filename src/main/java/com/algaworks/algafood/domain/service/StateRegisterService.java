package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;

@Service
public class StateRegisterService {
	
	@Autowired
	private StateRepository repository;
	
	public State save(State state) {
		return repository.save(state);
	}
	
	
}
