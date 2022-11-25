package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Kitchen;

//cozinhaRepository - não deve conter detalhes técnicos aqui
public interface KitchenRepository {
	
	List<Kitchen> all();
	Kitchen findById(Long id);
	Kitchen save(Kitchen kitchen);
	void remove(Long id);
	
}
