package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;
import com.algaworks.algafood.domain.service.StateRegisterService;

@RestController
@RequestMapping(value ="/states")
public class StateController {
	
	@Autowired
	private StateRepository repository;
	
	@Autowired
	private StateRegisterService service;
	
	@GetMapping
	public List<State> all(){
		return repository.all();
	}
	
	@GetMapping("/{stateId}")
	public ResponseEntity<State> findById(@PathVariable("stateId") Long id) {
		State state = repository.findById(id);
		
		if(state != null) {
			return ResponseEntity.ok(state);
		}	
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public State add(@RequestBody State state) {
		return service.save(state);
	}

}
