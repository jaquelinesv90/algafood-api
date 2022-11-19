package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;

@RestController
@RequestMapping(value = "/kitchens", produces = MediaType.APPLICATION_JSON_VALUE)
public class KitchenController {
	
	@Autowired
	private KitchenRepository repository;
	
	@GetMapping
	public List<Kitchen> all(){
		return repository.all();
	}
	
	
	@GetMapping("/{kitchenId}")
	public ResponseEntity<Kitchen> findById(@PathVariable("kitchenId") Long id) {
		Kitchen kitchen = repository.findById(id);
		
		if(kitchen != null) {
			return ResponseEntity.ok(kitchen);
		}
		
		//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Kitchen add(@RequestBody Kitchen kitchen) {
		return repository.add(kitchen);
	}
	
	public ResponseEntity<Kitchen> update(@PathVariable Long kitchenId, @RequestBody Kitchen kitchen){
		Kitchen currentKitchen = repository.findById(kitchenId);
		
		if(currentKitchen != null) {
			//currentKitchen.setName(kitchen.getName());
			BeanUtils.copyProperties(kitchen, currentKitchen, "id");
			
			currentKitchen = repository.save(currentKitchen);
			return ResponseEntity.ok(currentKitchen);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{kitchenId}")
	public ResponseEntity<Kitchen> delete(@PathVariable Long kitchenId){
		Kitchen kitchen = repository.findById(kitchenId);
		
	}
	
}
