package com.hbs.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentsTest {
	
	Payments pay;
	BookingDetails book;
	Transactions trans;
	@BeforeEach
	void SetUp() {
	pay = new Payments();
	book = new BookingDetails();
	trans = new Transactions();
	pay.setAmount(1234);
	pay.setPaymentId(12);
	pay.setBookingDetails(book);
	pay.setTransaction(trans);
	
	}
	@Test
	void testGetPaymentId() {
		assertEquals(12, pay.getPaymentId());
	}

	@Test
	void testGetAmount() {
		assertEquals(1234, pay.getAmount());
	}

	@Test
	void testGetBookingDetails() {
		assertEquals(0, book.getBookingId());
	}

	@Test
	void testGetTransaction() {
		assertEquals(0, trans.getTransactionId());
	}

}
