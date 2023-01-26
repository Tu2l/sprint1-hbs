package com.hbs.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hbs.dto.PaymentsDTO;
import com.hbs.entities.Payments;
import com.hbs.exceptions.PaymentsNotFoundException;
import com.hbs.repository.PaymentRepository;
import com.hbs.util.MapperUtil;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

	@Mock
	private PaymentRepository paymentRepository;

	@InjectMocks
	private PaymentServiceImpl paymentService;

	private Payments payment;
	private PaymentsDTO paymentDTO;

	@BeforeEach
	void setUp() {
		payment = new Payments();
		paymentDTO = new PaymentsDTO();
		paymentDTO.setBookingId(199);
		paymentDTO.setPaymentId(111);
		paymentDTO.setTransactionId(9999);
		paymentDTO.setAmount(7000);
	}

	@Test
	void testAddSuccess() {
		mockStatic(MapperUtil.class);
		when(paymentService.add(paymentDTO)).thenReturn(paymentDTO);
		PaymentsDTO result = paymentService.add(paymentDTO);
		assertEquals(199, result.getBookingId());
	}

	@Test
	void testAddAmountNegative() {
		paymentDTO.setAmount(-100);
		PaymentsDTO result = paymentService.add(paymentDTO);
		assertEquals(null, result);
	}

	@Test
	void testFindAll() {
		when(paymentRepository.findAll()).thenReturn(Arrays.asList(payment));
		List<PaymentsDTO> result = paymentService.findAll();
		assertEquals(1, result.size());
	}


	@Test
	void testFindByIdNotFound() {
		when(paymentRepository.findById(2)).thenReturn(java.util.Optional.empty());

		assertThrows(PaymentsNotFoundException.class, () -> paymentService.findById(2));
	}
}
