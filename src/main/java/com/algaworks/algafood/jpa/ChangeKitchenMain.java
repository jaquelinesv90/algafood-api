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

// AlteracaoCozinhaMain
public class ChangeKitchenMain {
	
	public static void main(String[] args) {
		ApplicationContext  applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);
		KitchenRepository kitchenRepository = applicationContext.getBean(KitchenRepository.class);
		
		Kitchen kitchen1 = new Kitchen();
		kitchen1.setName("German");
		
		Kitchen kitchen2 = new Kitchen();
		kitchen2.setName("Japanese");
		
		kitchenRepository.add(kitchen1);
		kitchenRepository.add(kitchen2);
		
		System.out.printf("teste",kitchen1.getId(), kitchen1.getName());
	}

}
