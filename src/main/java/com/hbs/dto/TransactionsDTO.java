package com.hbs.dto;

import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class TransactionsDTO {
	@Min(value = 0, message = "Invalid id")
	private int transactionId;
	
	@Min(value = 0, message = "amount must not be empty")
	private double amount;

}