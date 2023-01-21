package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.State;

public interface StateRepository {
	
	List<State> all();
	State findById(Long id);
	State save(State state);
	void remove(Long id);

}
