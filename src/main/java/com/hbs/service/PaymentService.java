package com.hbs.service;

import java.util.List;

import com.hbs.dto.PaymentsDTO;
import com.hbs.exceptions.PaymentsNotFoundException;

public interface PaymentService {
	public PaymentsDTO add(PaymentsDTO dto);

	public List<PaymentsDTO> findAll();

	public PaymentsDTO findById(int id) throws PaymentsNotFoundException;

}
