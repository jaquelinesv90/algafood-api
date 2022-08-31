package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.repository.CityRepository;

public class QueryCityMain {
	
	public static void main(String[] args) {
		ApplicationContext  applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CityRepository cityRepository = applicationContext.getBean(CityRepository.class);
		var allCities = cityRepository.all();
		
		allCities.forEach(city -> System.out.printf(city.getName(), city.getState().getName()));
		
	}	
}