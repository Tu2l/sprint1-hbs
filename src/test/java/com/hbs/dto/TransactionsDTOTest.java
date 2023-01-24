package com.hbs.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransactionsDTOTest {
	
	
	private TransactionsDTO dto;
	
	
	@BeforeEach
	void setup() {
		dto = new TransactionsDTO();
		dto.setTransactionId(575463);
		dto.setAmount(550);
		
	}

	@Test
	void testGetTransactionId() {
		assertEquals(575463, dto.getTransactionId());
	}

	@Test
	void testGetAmount() {
		assertEquals(550, dto.getAmount());
	}
	
	//Negative tests
	@Test
	void testGetTransactionIdNegative() {
		assertNotEquals(57546, dto.getTransactionId());
	}


}
