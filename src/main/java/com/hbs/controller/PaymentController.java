package com.hbs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbs.dto.PaymentsDTO;
import com.hbs.service.PaymentService;
import com.hbs.util.MapperUtil;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping
	public ResponseEntity<PaymentsDTO> add(@Valid @RequestBody PaymentsDTO paymentDto) {
		return new ResponseEntity<>(MapperUtil.mapToPaymentDto(paymentService.add(MapperUtil.mapToPayment(paymentDto))),
				HttpStatus.CREATED);
	}
}
