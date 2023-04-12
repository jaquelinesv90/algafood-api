package com.algaworks.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Embeddable
public class Address {

	@Column(name= "zipcode_address")
	private String zipCode;
	
	@Column(name = "logradouro_address")
	private String logradouro;
	
	@Column(name = "number_address")
	private String number;
	
	@Column(name = "complement_address")
	private String complement;
	
	@Column(name = "neighborhood_address")
	private String neighborhood;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id_address")
	private City city;
	
}
