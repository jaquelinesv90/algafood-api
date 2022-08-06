package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;

//ConsultaCozinhaMain
public class QueryKitchenMain {
	
	public static void main(String[] args) {
		ApplicationContext  applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);
		
		var kitchens = applicationContext.getBean(KitchenRepository.class);
		
		List<Kitchen> allKitchens = kitchens.all();
		
		allKitchens.forEach(kitchen -> System.out.println(kitchen.getName()));
	}
}