package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.PaymentWay;

public interface PaymentWayRepository {
	
	List<PaymentWay> all();
	PaymentWay findById(Long id);
	PaymentWay save(PaymentWay payment);
	void remove(PaymentWay payment);
	
}
