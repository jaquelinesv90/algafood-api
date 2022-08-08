package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.State;

public interface StateRepository {
	
	List<State> all();
	State findById(Long id);
	State add(State state);
	void remove(State state);

}
