package com.hbs.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hbs.entities.BookingDetails;
import com.hbs.entities.Payments;
import com.hbs.entities.Transactions;
import com.hbs.repository.PaymentRepository;

class PaymentServiceImplTest {

	@Mock
	private PaymentRepository payRepoMock;

	@InjectMocks
	private PaymentServiceImpl serviceMock;

	private Payments payment;

	private Transactions transaction;

	@BeforeEach
	void setup() {
		payment = new Payments();
		payment.setAmount(500);
		payment.setBookingDetails(new BookingDetails());
		payment.setPaymentId(999);

		transaction = new Transactions();
		transaction.setTransactionId(1910);
		transaction.setAmount(19000);
		payment.setTransaction(transaction);
		MockitoAnnotations.openMocks(this);

	}

	@Test
	void addPaymentTest() {
		when(payRepoMock.save(payment)).thenReturn(payment);
		Payments returnedPayment = serviceMock.add(payment);
		assertEquals(payment, returnedPayment);
		verify(payRepoMock, times(1)).save(payment);
	}

}
