package com.hbs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbs.dto.TransactionsDTO;
import com.hbs.service.TransactionService;
import com.hbs.util.MapperUtil;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping
	public ResponseEntity<TransactionsDTO> add(@Valid @RequestBody TransactionsDTO transactionDto) {
		return new ResponseEntity<>(
				MapperUtil.mapToTransactionDto(transactionService.add(MapperUtil.mapToTransaction(transactionDto))),
				HttpStatus.CREATED);
	}
}
