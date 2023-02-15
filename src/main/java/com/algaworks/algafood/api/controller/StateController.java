package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;
import com.algaworks.algafood.domain.service.StateRegisterService;
import com.algaworks.domain.exception.EntityInUseException;
import com.algaworks.domain.exception.EntityNotFoundException;

@RestController
@RequestMapping(value ="/states")
public class StateController {
	
	@Autowired
	private StateRepository repository;
	
	@Autowired
	private StateRegisterService service;
	
	@GetMapping
	public List<State> all(){
		return repository.findAll();
	}
	
	@GetMapping("/{stateId}")
	public ResponseEntity<State> findById(@PathVariable("stateId") Long id) {
		Optional<State> state = repository.findById(id);
		
		if(state.isEmpty() ) {
			return ResponseEntity.ok(state.get());
		}	
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public State add(@RequestBody State state) {
		return service.save(state);
	}
	
	@PutMapping("/{stateId}")
	public ResponseEntity<State> update(@PathVariable Long stateId, @RequestBody State state){
		Optional<State> currentState = repository.findById(stateId);
		
		if(currentState != null) {
			
			BeanUtils.copyProperties(state, currentState, "id");
			
			State savedState = service.save(currentState.get());
			
			return ResponseEntity.ok(savedState);
		}
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{stateId}")
	public ResponseEntity<?> delete(@PathVariable Long stateId) {
		
		try {
			service.delete(stateId);
			
			return ResponseEntity.noContent().build();
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}

}
