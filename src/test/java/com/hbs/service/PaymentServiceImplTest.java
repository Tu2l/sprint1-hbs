package com.hbs.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hbs.dto.PaymentsDTO;
import com.hbs.entities.BookingDetails;
import com.hbs.entities.Payments;
import com.hbs.entities.Transactions;
import com.hbs.repository.PaymentRepository;
import com.hbs.serviceimpl.PaymentServiceImpl;
import com.hbs.util.MapperUtil;

class PaymentServiceImplTest {

	@Mock
	private PaymentRepository payRepoMock;

	@InjectMocks
	private PaymentServiceImpl serviceMock;

	private PaymentsDTO paymentsDTO;

	private Transactions transaction;

	@BeforeEach
	void setup() {
		paymentsDTO = new PaymentsDTO();
		paymentsDTO.setAmount(500);
		paymentsDTO.setPaymentId(190);
		paymentsDTO.setTransactionId(90909098);
		MockitoAnnotations.openMocks(this);

	}

	@Test
	void testAddPaymentSuccess() {
		Payments payment = MapperUtil.mapToPayment(paymentsDTO);
		when(payRepoMock.save(any(Payments.class))).thenReturn(payment);
		PaymentsDTO result = serviceMock.add(paymentsDTO);
		assertNotNull(result);
		assertEquals(paymentsDTO, result);
		verify(payRepoMock, times(1)).save(any(Payments.class));
	}

	@Test
	void testAddPaymentFailure() {
	    paymentsDTO.setAmount(-500);
	    verify(payRepoMock, never()).save(any(Payments.class));
	}


}
