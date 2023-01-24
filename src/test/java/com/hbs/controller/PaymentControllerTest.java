package com.hbs.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hbs.dto.PaymentsDTO;
import com.hbs.service.PaymentService;
@ExtendWith(MockitoExtension.class)
class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;

    

    @BeforeEach
    public void setUp() {
    	 MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPaymentSuccess() throws Exception {
        PaymentsDTO paymentDto = new PaymentsDTO();
        paymentDto.setAmount(100);
        paymentDto.setBookingId(1);
        paymentDto.setTransactionId(1);
        when(paymentService.add(paymentDto)).thenReturn(paymentDto);
        verify(paymentService, times(1)).add(paymentDto);
    }
 /*   
    @Test
    void testAddPayment_InvalidAmount() throws Exception {
        PaymentsDTO paymentDto = new PaymentsDTO();
        paymentDto.setAmount(0);
        paymentDto.setBookingId(1);
        paymentDto.setTransactionId(1);
        
        
    }
 */   
    
}