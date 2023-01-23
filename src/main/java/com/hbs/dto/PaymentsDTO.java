package com.hbs.dto;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class PaymentsDTO {
	@JsonProperty(access = Access.READ_ONLY)
	private int paymentId;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Min(value = 1, message = "amount is invalid")
	private double amount;

	@JsonProperty(access = Access.READ_ONLY)
	private int bookingId;
	
	@JsonProperty(access = Access.READ_ONLY)
	private int transactionId;
}