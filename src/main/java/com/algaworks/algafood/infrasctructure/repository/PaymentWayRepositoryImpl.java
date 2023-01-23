package com.algaworks.algafood.infrasctructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.PaymentWay;
import com.algaworks.algafood.domain.repository.PaymentWayRepository;

@Component
public class PaymentWayRepositoryImpl implements PaymentWayRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<PaymentWay> all() {
		return manager.createQuery("from PaymentWay", PaymentWay.class).getResultList();
	}

	@Override
	public PaymentWay findById(Long id) {
		return manager.find(PaymentWay.class, id);
	}

	@Override
	@Transactional
	public PaymentWay save(PaymentWay payment) {
		return manager.merge(payment);
	}

	@Override
	@Transactional
	public void remove(PaymentWay payment) {
		payment = findById(payment.getId());
		manager.remove(payment);
	}
}
