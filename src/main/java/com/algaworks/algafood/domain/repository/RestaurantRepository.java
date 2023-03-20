package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurant;

@Repository
public interface RestaurantRepository 
		extends CustomJpaRepository<Restaurant,Long>, RestaurantRepositoryQueries,
		JpaSpecificationExecutor<Restaurant>{
	
	List<Restaurant> findByShippingTaxBetween(BigDecimal initialFee, BigDecimal finalFee);
	
	//@Query(" from Restaurant where name like %:name% ")
	Optional<Restaurant> findByFirstname(String name, Long id);
	
	List<Restaurant> findByNameContainingAndKitchenId(String name, Long kitchen);
	
	List<Restaurant>queryByShippingTaxBetween(BigDecimal initialFee, BigDecimal finalFee);
	
}
