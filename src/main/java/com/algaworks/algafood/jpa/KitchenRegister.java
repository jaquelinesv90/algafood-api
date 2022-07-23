package com.algaworks.algafood.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Kitchen;

//CadastroCozinha
@Component
public class KitchenRegister {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Kitchen> list(){
		return manager.createQuery("from kitchen", Kitchen.class).getResultList();
	}
	
	@Transactional
	public Kitchen toAdd(Kitchen kitchen) {
		return manager.merge(kitchen);
	}
	
	public Kitchen find(Long id) {
		return manager.find(Kitchen.class, id);
	}
}
