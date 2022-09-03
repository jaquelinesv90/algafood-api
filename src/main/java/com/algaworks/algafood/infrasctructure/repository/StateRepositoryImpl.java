package com.algaworks.algafood.infrasctructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;

@Component
public class StateRepositoryImpl implements StateRepository{
	
	@PersistenceContext
	private EntityManager manager;
		
	@Override
	public List<State> all() {
		return manager.createQuery("from State", State.class).getResultList();	
	}

	@Override
	public State findById(Long id) {
		return manager.find(State.class, id);
	}

	@Override
	@Transactional
	public State add(State state) {
		return manager.merge(state);
	}

	@Override
	@Transactional
	public void remove(State state) {
		state = findById(state.getId());
		manager.remove(state);
	}
}
