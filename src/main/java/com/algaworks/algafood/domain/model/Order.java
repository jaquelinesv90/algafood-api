package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name= "order")
public class Order {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable= false)
	private BigDecimal subTotal;
	
	@Column(nullable= false)
	private BigDecimal shippingTax;
	
	@Column(nullable= false)
	private LocalDateTime createdDate;
	
	private LocalDateTime confirmationDate;
	
	private LocalDateTime cancellationDate;
	
	private LocalDateTime deliveryDate;
	
	@Embedded
	private Address deliveryAddress;
	
	private OrderStatus status;
	
	@ManyToOne
	@JoinColumn(name = "payment_way")
	private PaymentWay paymentWay;
	
	@ManyToOne
	@JoinColumn(name ="restaurant")
	private Restaurant restaurant;
	
	@ManyToOne
	@JoinColumn(name ="user", nullable = false)
	private User user;
	
	@OneToMany(mappedBy = "order")
	private List<OrderItem> items= new ArrayList<>();
	
}
