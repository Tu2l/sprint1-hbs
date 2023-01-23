package com.hbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbs.dto.PaymentsDTO;
import com.hbs.entities.Payments;
import com.hbs.repository.PaymentRepository;
import com.hbs.util.MapperUtil;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public PaymentsDTO add(PaymentsDTO dto) {
	    if(dto.getAmount() < 0) {
	        return null;
	    }
	    Payments payments = MapperUtil.mapToPayment(dto);
	    return MapperUtil.mapToPaymentDto(paymentRepository.save(payments));
	}


}
