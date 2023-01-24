package com.hbs.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "booking_details")public class BookingDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "booking_id")
	private int bookingId;

	@OneToOne
	@JoinColumn(name = "fk_user", referencedColumnName = "user_id")
	private User user;

	@OneToOne
	@JoinColumn(name = "fk_hotel", referencedColumnName = "hotel_id")
	private Hotel hotel;

	@Column(name = "booked_from", nullable = false)
	private LocalDate bookedFrom;

	@Column(name = "booked_to", nullable = false)
	private LocalDate bookedTo;

	@Column(name = "no_of_adults", nullable = false)
	private int noOfAdults;

	@Column(name = "no_of_children", nullable = false)
	private int noOfChildren;

	@Column(name = "amount", nullable = false)
	private double amount;

	@ManyToMany
	@JoinTable(name = "booking_rooms", inverseJoinColumns = @JoinColumn(name = "room_id"), joinColumns = @JoinColumn(name = "booking_id"))
	private List<RoomDetails> roomList;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "booking_payments", inverseJoinColumns = @JoinColumn(name = "payment_id"), joinColumns = @JoinColumn(name = "booking_id"))
	private List<Payments> paymentList = new ArrayList<>();

	public void setPaymentList(List<Payments> paymentList) {

		if (paymentList == null)
			return;

		// generating payments
		this.paymentList = paymentList;
		for (Payments payment : paymentList) {
			payment.setBookingDetails(this);

			// generating transactions
			Transactions transaction = new Transactions();
			transaction.setAmount(payment.getAmount());
			payment.setTransaction(transaction);

			// calculating total amount
			this.amount += payment.getAmount();
		}
	}

}