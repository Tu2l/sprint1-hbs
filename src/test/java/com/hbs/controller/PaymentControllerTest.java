package com.hbs.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.hbs.dto.PaymentsDTO;
import com.hbs.exceptions.PaymentsNotFoundException;
import com.hbs.service.PaymentService;

@ExtendWith(MockitoExtension.class)
class PaymentControllerTest {

	@Mock
	private PaymentService paymentService;
	@InjectMocks
	private PaymentController paymentController;
	private List<PaymentsDTO> paymentList=new ArrayList<PaymentsDTO>();
	private PaymentsDTO payment1;
	private PaymentsDTO payment2;

	@BeforeEach
	public void setUp() {
		payment1 = new PaymentsDTO();
		payment1.setPaymentId(1);
		payment1.setAmount(100);
		payment1.setBookingId(1090);
		payment1.setTransactionId(19999);

		payment2 = new PaymentsDTO();
		payment2.setPaymentId(2);
		payment2.setAmount(200);
		payment2.setBookingId(2);
		payment2.setTransactionId(2);
		
		paymentController = new PaymentController();
		MockitoAnnotations.openMocks(this);
		paymentList.add(payment1);
		paymentList.add(payment2);
	}

	@Test
	void testAdd() {
		when(paymentService.add(payment1)).thenReturn(payment1);
		paymentController.add(payment1);
		verify(paymentService, times(1)).add(payment1);
		assertEquals(100, paymentService.add(payment1).getAmount());
	}
	
	@Test
	void testFindAll() {
		when(paymentService.findAll()).thenReturn(paymentList);
		paymentController.findAll();
		verify(paymentService,times(1)).findAll();
		assertEquals(2,paymentService.findAll().size());
	}
	
	@Test
	void testFindById() throws PaymentsNotFoundException {
		when(paymentService.findById(1)).thenReturn(payment1);
		paymentController.findById(1);
		assertEquals(HttpStatus.FOUND,paymentController.findById(1).getStatusCode());
	}
}