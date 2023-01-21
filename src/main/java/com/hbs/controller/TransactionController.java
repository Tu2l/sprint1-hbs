package com.hbs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbs.entities.Transactions;
import com.hbs.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping
	public ResponseEntity<Transactions> add(@Valid @RequestBody Transactions transaction) {
		return new ResponseEntity<>(transactionService.add(transaction), HttpStatus.CREATED);
	}
}
