package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {
	
	@Autowired
	private KitchenRepository repository;
	
	@GetMapping
	public List<Kitchen> all(){
		return repository.all();
	}
	
	@GetMapping("/{kitchenId}")
	public Kitchen findById(@PathVariable("kitchenId") Long id) {
		return repository.findById(id);
	}
	
	
}
