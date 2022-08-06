package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepository;

public class QueryRestaurantMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
		.web(WebApplicationType.NONE)
		.run(args);
		
		var restaurantRepository = applicationContext.getBean(RestaurantRepository.class);
		
		List<Restaurant> allRestaurants = restaurantRepository.all();

		allRestaurants.forEach(restaurant -> System.out.printf(restaurant.getName()));
	}
}
