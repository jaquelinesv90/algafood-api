package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;

/**
 * @author jaqueline Lmk
 * @version 1.0
 */

// InclusaoCozinhaMain
public class InclusionKitchenMain {
	
	public static void main(String[] args) {
		ApplicationContext  applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);
		KitchenRepository kitchenRepository = applicationContext.getBean(KitchenRepository.class);
		
		Kitchen kitchen = new Kitchen();
		kitchen.setId(1L);
		kitchen.setName("German");
		
		kitchenRepository.add(kitchen);
		
		
	}

}
