package com.hbs.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import com.hbs.dto.TransactionsDTO;
import com.hbs.service.TransactionService;


class TransactionControllerTest {

	@Mock
	private TransactionService service;
	
	@InjectMocks
	private TransactionController controller;
	
	private TransactionsDTO dto;
	
	@BeforeEach
	void setup() {
		dto=new TransactionsDTO();
		dto.setTransactionId(1234);
		dto.setAmount(10000);
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testAdd() {
		when(service.add(dto)).thenReturn(dto);
		assertEquals(HttpStatus.CREATED,controller.add(dto).getStatusCode());
		assertEquals(10000,service.add(dto).getAmount());
	}
}
