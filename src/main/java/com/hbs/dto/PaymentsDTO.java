package com.hbs.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentsDTO {
	@Min(value = 0, message = "Invalid payment id") 
	private int paymentId;
	
	@NotNull(message = " booking id is invalid") 
	private int bookingId;
	
	@Min(value = 0, message = " transaction id is invalid")
	private int transactionId;
}