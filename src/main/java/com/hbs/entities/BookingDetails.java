package com.hbs.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
=======
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
>>>>>>> branch 'main' of https://github.com/Tu2l/sprint1-hbs.git
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
@Table(name = "booking_details_table")
public class BookingDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "booking_id")
	private int bookingId;

	@OneToOne
	@JoinColumn(name = "fk_user", referencedColumnName = "user_id")
//	@Column(name = "user_id")
	private User userId;

	@OneToOne
	@JoinColumn(name = "fk_hotel", referencedColumnName = "hotel_id")
//	@Column(name = "hotel_id", nullable = false, unique = true)
	private Hotel hotelId;

	@Column(name = "booked_from", nullable = false, unique = true)
	private Date bookedFrom;

	@Column(name = "booked_to", nullable = false, unique = true)
	private Date bookedTo;

	@Column(name = "no_of_adults", nullable = false, unique = true)
	private int noOfAdults;

	@Column(name = "no_of_children", nullable = false, unique = true)
	private int noOfChildren;

	@Column(name = "amount", nullable = false)
	private double amount;
	
	@OneToMany
	@Column(name = "room_id", nullable = false)
	private List<RoomDetails> roomsList;
	
	@OneToMany
	@Column(name = "fk_payment", nullable = false)
	private List<Payments> paymentList;


}