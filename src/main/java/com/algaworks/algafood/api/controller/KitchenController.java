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

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.domain.service.KitchenRegisterService;
import com.algaworks.domain.exception.EntityInUseException;
import com.algaworks.domain.exception.EntityNotFoundException;

@RestController
@RequestMapping(value = "/kitchens")
public class KitchenController {
	
	@Autowired
	private KitchenRepository repository;
	
	@Autowired
	private KitchenRegisterService service;
	
	@GetMapping
	public List<Kitchen> all(){
		return repository.findAll();
	}
	
	@GetMapping("/{kitchenId}")
	public Kitchen findById(@PathVariable("kitchenId") Long id) {
		return service.seekOrFail(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Kitchen add(@RequestBody Kitchen kitchen) {
		return service.save(kitchen);
	}
	
	@PutMapping("/{kitchenId}")
	public Kitchen update(@PathVariable Long kitchenId, @RequestBody Kitchen kitchen){
		
		Kitchen currentKitchen = service.seekOrFail(kitchenId);
		
		BeanUtils.copyProperties(kitchen, currentKitchen,"id");
		
		return service.save(currentKitchen);
	}
	/*	
	@DeleteMapping("/{kitchenId}")
	public ResponseEntity<Kitchen> delete(@PathVariable Long kitchenId){
		
		try {
			service.remove(kitchenId);
			
			return ResponseEntity.noContent().build();
			
		}catch(EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
			
		}catch(EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	} */
	
	@DeleteMapping("/{kitchenId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long kitchenId) {
		service.remove(kitchenId);
	}
	
}
