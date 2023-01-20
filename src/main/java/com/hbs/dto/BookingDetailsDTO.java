package com.hbs.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingDetailsDTO {
	@Min(value = 0, message = "Booking Id must greater than zero.")
	@JsonProperty(access = Access.WRITE_ONLY)
	private int bookingId;

	@Min(value = 0, message = "User Id must be greater than zero")
	private int userId;

	@NotNull(message = "Hotel is required.")
	private int hotelId;

	@NotNull(message = "Booked from date is required.")
	@FutureOrPresent(message = "Booked from date must be in the past.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate bookedFrom;

	@NotNull(message = "Booked to date is required.")
	@Future(message = "Booked to date must be in the future.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate bookedTo;

	@NotNull(message = "Number of adults is required.")
	private int noOfAdults;

	@NotNull(message = "Number of children is required.")
	private int noOfChildren;

	@Min(value = 0, message = "Amount must be greater than zero.")
	private double amount;

	@Size(min = 1, message = "room list must not be empty")
	private List<Integer> roomIdList;

	@Size(min = 1, message = "payment list must not be empty")
	private List<Integer> paymentIdList;
}
