package com.algaworks.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.State;

@Repository
public interface StateRepository extends CustomJpaRepository<State, Long>{
	


}
