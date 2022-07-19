package com.algaworks.algafood.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Kitchen;

@Component
public class KitchenRegister {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Kitchen> list(){
		return manager.createQuery("from kitchen", Kitchen.class).getResultList();
		
	}
	
	public Kitchen toAdd(Kitchen kitchen) {
		return manager.merge(kitchen);
	}

}
