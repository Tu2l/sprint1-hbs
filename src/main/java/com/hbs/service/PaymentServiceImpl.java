package com.hbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbs.entities.Payments;
import com.hbs.repository.IPaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private IPaymentRepository paymentRepository;

	@Override
	public Payments addPayment(Payments payment) {

		return paymentRepository.save(payment);
	}

}
