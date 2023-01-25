package com.hbs.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbs.dto.PaymentsDTO;
import com.hbs.exceptions.PaymentsNotFoundException;
import com.hbs.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping
	public ResponseEntity<PaymentsDTO> add(@Valid @RequestBody PaymentsDTO paymentDto) {
		return new ResponseEntity<>(paymentService.add(paymentDto), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<PaymentsDTO>> findAll() {
		return new ResponseEntity<>(paymentService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PaymentsDTO> findById(@PathVariable int id) throws PaymentsNotFoundException {
		PaymentsDTO paymentDto = paymentService.findById(id);
		return new ResponseEntity<>(paymentDto, HttpStatus.FOUND);
	}
}
