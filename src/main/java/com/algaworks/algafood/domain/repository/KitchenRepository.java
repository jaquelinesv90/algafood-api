package com.algaworks.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Kitchen;

//cozinhaRepository - não deve conter detalhes técnicos aqui
@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long>{
	

	
}
