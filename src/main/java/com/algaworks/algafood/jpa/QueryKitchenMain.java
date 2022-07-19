package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Kitchen;

public class QueryKitchenMain {
	
	public static void main(String[] args) {
		ApplicationContext  applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);
		KitchenRegister kitchenRegister = applicationContext.getBean(KitchenRegister.class);
		
		Kitchen kitchen1 = new Kitchen();
		kitchen1.setName("German");
		
		Kitchen kitchen2 = new Kitchen();
		kitchen2.setName("Japanese");
		
		kitchenRegister.toAdd(kitchen1);
		kitchenRegister.toAdd(kitchen2);
		
	
		
	}

}
