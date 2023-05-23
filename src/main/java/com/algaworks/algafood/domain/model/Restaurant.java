package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurant {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@Column(name = "name", length = 30, nullable = false)
	private String name;
	
	@Column(name = "shipping_tax", nullable = false)
	private BigDecimal shippingTax;
	
	// estamos ignorando a propriedade LazyInitializer, não serializa ela
	@JsonIgnore
	//	@JsonIgnoreProperties("hibernateLazyInitializer")
	@ManyToOne //(fetch = FetchType.LAZY)
	@JoinColumn(name = "kitchen_id", nullable = false)
	private Kitchen kitchen;
	
	@JsonIgnore   
	@Embedded
	private Address address;
	
	@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime createdDate;
	
	@JsonIgnore
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime lastUpdatedDate;
	
	// o relacionamento é muitos-para-muitos e no name do joinTable é o nome da tabela intermediária
	// essa tabela intermediária vai ser criada em tempo de execução
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "restaurant_payment_way",
			joinColumns = @JoinColumn(name = "restaurant_id"),
			inverseJoinColumns = @JoinColumn(name = "payment_way_id"))
	private List<PaymentWay> listPaymentWay = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="restaurant")
	private List<Product> products = new ArrayList<>();
	
	/*
	@OneToMany(mappedBy = "order")
	private List<Order> orders; */
	
}
