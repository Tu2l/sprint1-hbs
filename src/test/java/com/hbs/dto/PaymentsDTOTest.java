package com.hbs.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentsDTOTest {
	
	private PaymentsDTO dto;
	
	@BeforeEach
	void setup() {
		dto = new PaymentsDTO();
		dto.setPaymentId(19879);
		dto.setTransactionId(708090);
		dto.setBookingId(189);
		dto.setAmount(980);
	}

	@Test
	void testGetPaymentId() {
		assertEquals(19879, dto.getPaymentId());
	}

	@Test
	void testGetAmount() {
		assertEquals(980, dto.getAmount());
	}

	@Test
	void testGetBookingId() {
		assertEquals(189, dto.getBookingId());
	}

	@Test
	void testGetTransactionId() {
		assertEquals(708090, dto.getTransactionId());
	}
	
	//negative testscenarios
	@Test
	void testGetPaymentIdNegative() {
		assertNotEquals(1987, dto.getPaymentId());
	}
	

}
