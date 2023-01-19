package com.hbs.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "booking_details_table")
public class BookingDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;

	@Column(name = "user_id")
	private User userId;

	@Column(name = "hotel_id", nullable = false, unique = true)
	private Hotel hotelId;

	@Column(name = "room_id", nullable = false, unique = true)
	private User roomId;

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

}