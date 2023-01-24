package com.hbs.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;

import lombok.Data;

import lombok.EqualsAndHashCode;

import lombok.NoArgsConstructor;

import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@ToString(exclude = {"bookingDetails"})
@Table(name = "payments")
public class Payments {

	@Id
	@Column(name = "payment_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int paymentId;
	
	@Transient
	private double amount;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_booking_id")
	private BookingDetails bookingDetails;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_transaction", referencedColumnName = "transaction_id")
	private Transactions transaction;

}