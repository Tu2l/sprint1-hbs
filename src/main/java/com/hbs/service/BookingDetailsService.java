package com.hbs.service;

import java.util.List;

import com.hbs.entities.BookingDetails;
import com.hbs.exceptions.BookingDetailsNotFoundException;

public interface BookingDetailsService {
	BookingDetails addBookingDetails(BookingDetails bookingDetails);

	BookingDetails updateBookingDetails(BookingDetails bookingDetails)throws BookingDetailsNotFoundException;

	BookingDetails removeBookingDetails(BookingDetails bookingDetails)throws BookingDetailsNotFoundException;

	List<BookingDetails> showAllBookingDetails();

	BookingDetails showBookingDetails(int bookingDetailsId)throws BookingDetailsNotFoundException;
}
