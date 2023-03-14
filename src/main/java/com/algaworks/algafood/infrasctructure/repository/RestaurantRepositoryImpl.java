package com.algaworks.algafood.infrasctructure.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algaworks.algafood.domain.model.Restaurant;

@Repository
public class RestaurantRepositoryImpl {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Restaurant> find(String name, 
			BigDecimal initialShippingFee, BigDecimal finalShippingFee){
		
		var jpql = new StringBuilder();
		jpql.append("from Restaurant where 0 = 0 ");
		
		var parameter = new HashMap<String, Object>();
		
		if(StringUtils.hasLength(name)) {
			jpql.append("where name like :name ");
			parameter.put("name", "%" + name + "%");
		}
		
		if(initialShippingFee != null) {
			jpql.append("and shippingTax >= initialTax ");
			parameter.put("initialFee", initialShippingFee);
		}
		
		if(finalShippingFee != null) {
			jpql.append("and shippingTax <= finalTax ");
			parameter.put(name, finalShippingFee);
		}
		
		TypedQuery<Restaurant> query = manager
				.createQuery(jpql.toString(), Restaurant.class);
		
		parameter.forEach((key, value) -> query.setParameter(key, value));
		
		return query.getResultList();
	}
	
	// usando o criteria api - consultas complexas
	public List<Restaurant> find(String name,
			BigDecimal initialShippingFee, BigDecimal finalShippingFee, int i){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
		Root<Restaurant> root = criteria.from(Restaurant.class);
		
		Predicate namePredicate = builder.like(root.get("name"), "%" + name + "%");
		
		Predicate initialShippingFeePredicate = builder
				.greaterThanOrEqualTo(root.get("shippingTax"), initialShippingFee);
		
		Predicate finalShippingFeePredicate = builder
				.lessThanOrEqualTo(root.get("shippingTax"), finalShippingFee);
		
		criteria.where(namePredicate, initialShippingFeePredicate, finalShippingFeePredicate);
		
		TypedQuery<Restaurant> query = manager.createQuery(criteria);
		return query.getResultList();	
	}
	

}
