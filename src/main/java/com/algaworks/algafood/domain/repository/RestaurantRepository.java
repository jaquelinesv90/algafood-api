package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Long>{
	
	List<Restaurant> findByShippingTaxBetween(BigDecimal initialFee, BigDecimal finalFee);
	
	List<Restaurant> findByNameContainingAndKitchenId(String name, Long kitchen);
	
	List<Restaurant>queryByShippingTaxBetween(BigDecimal initialFee, BigDecimal finalFee);
	
}
