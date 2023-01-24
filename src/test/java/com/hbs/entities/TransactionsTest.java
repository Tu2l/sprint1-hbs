package com.hbs.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransactionsTest {
	 Transactions trans;
	@BeforeEach
	void setUp() {
		trans = new Transactions();
		trans.setAmount(12000);
		trans.setTransactionId(1);
	}
	
	@Test
	void testGetTransactionId() {
		assertEquals(1, trans.getTransactionId());
	}

	@Test
	void testGetAmount() {
		assertEquals(12000, trans.getAmount());
	}

}
