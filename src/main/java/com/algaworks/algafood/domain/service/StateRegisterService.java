package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;
import com.algaworks.domain.exception.EntityInUseException;
import com.algaworks.domain.exception.EntityNotFoundException;

@Service
public class StateRegisterService {
	
	private static final String MSG_STATE_NOT_FOUND = "There is no register for state with the code %d";
	
	private static final String MSG_STATE_BEEN_USED = "State cannot be removed, it's been used";
	
	@Autowired
	private StateRepository repository;
	
	public State save(State state) {
		return repository.save(state);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
			
		}catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
			
		}catch(EntityNotFoundException e) {
			throw new EntityNotFoundException(String.format(MSG_STATE_NOT_FOUND,id));
		
		}catch(DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(MSG_STATE_BEEN_USED, id));
		}
	}
	
	
	public State seekOrFail(Long stateId) {
		return repository.findById(stateId)
				.orElseThrow(() -> new EntityNotFoundException(String.format(MSG_STATE_NOT_FOUND, stateId)));
	}
}
