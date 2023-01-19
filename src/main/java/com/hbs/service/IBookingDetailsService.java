package com.hbs.service;

import java.util.List;

import com.hbs.entities.BookingDetails;

public interface IBookingDetailsService {
	BookingDetails addBookingDetails(BookingDetails bookingDetails);

	BookingDetails updateBookingDetails(BookingDetails bookingDetails);

	BookingDetails removeBookingDetails(BookingDetails bookingDetails);

	List<BookingDetails> showAllBookingDetails();

	BookingDetails showBookingDetails(BookingDetails bookingDetails);
}
