package com.hbs.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbs.dto.PaymentsDTO;
import com.hbs.entities.Payments;
import com.hbs.exceptions.PaymentsNotFoundException;
import com.hbs.repository.PaymentRepository;
import com.hbs.service.PaymentService;
import com.hbs.util.MapperUtil;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public PaymentsDTO add(PaymentsDTO dto) {
		if (dto.getAmount() < 0) {
			return null;
		}
		Payments payments = MapperUtil.mapToPayment(dto);
		return MapperUtil.mapToPaymentDto(paymentRepository.save(payments));
	}

	@Override
	public List<PaymentsDTO> findAll() {
		List<Payments> payments = paymentRepository.findAll();
		return payments.stream().map(MapperUtil::mapToPaymentDto).collect(Collectors.toList());
	}

	@Override
	public PaymentsDTO findById(int id) throws PaymentsNotFoundException {
		Optional<Payments> payments = paymentRepository.findById(id);

		if (payments.isEmpty()) {
			throw new PaymentsNotFoundException("Payment Not Found with Id: " + id);
		} else {
			return MapperUtil.mapToPaymentDto(payments.get());
		}
	}
}
