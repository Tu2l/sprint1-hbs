package com.hbs.entities;

import javax.persistence.Column;

import javax.persistence.Entity;

import javax.persistence.Id;

import javax.persistence.Table;

import lombok.AllArgsConstructor;

import lombok.Data;

import lombok.EqualsAndHashCode;

import lombok.NoArgsConstructor;

import lombok.ToString;

@Data

@AllArgsConstructor

@NoArgsConstructor

@ToString

@EqualsAndHashCode

@Entity

@Table(name = "payments")

public class Payments {

	@Id

	@Column(name = "payment_id")

	private int paymentId;

	@Column(name = "booking_id")

	private int bookingId;

	@Column(name = "transaction_id")

	private int transactionId;

}