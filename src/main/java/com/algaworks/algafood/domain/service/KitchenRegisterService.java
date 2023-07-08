package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.domain.exception.EntityInUseException;
import com.algaworks.domain.exception.EntityNotFoundException;
import com.algaworks.domain.exception.KitchenNotFoundException;

@Service
public class KitchenRegisterService {
	
	private static final String MSG_KITCHEN_BEEN_USED = "kitchen code cannot be removed, it's been used";
	
	@Autowired 
	private KitchenRepository repository;
	
	public Kitchen save(Kitchen kitchen) {
		return repository.save(kitchen);
	}
	
	//uma classe de serviço não pode retornar um ResponseEntity,de forma alguma.
	public void remove(Long id) {
		try {
			repository.deleteById(id);
		
		}catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
			
		}catch(EntityNotFoundException e) {
			throw new KitchenNotFoundException(id);
			
		}catch(DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(MSG_KITCHEN_BEEN_USED,
							id));
		}	
	}
	
	public Kitchen seekOrFail(Long kitchenId) {
		return repository.findById(kitchenId)
				.orElseThrow(() -> new KitchenNotFoundException(kitchenId));
	}

}
