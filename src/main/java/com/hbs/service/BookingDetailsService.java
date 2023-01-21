package com.hbs.service;

import java.util.List;

import com.hbs.entities.BookingDetails;
import com.hbs.exceptions.BookingDetailsNotFoundException;

public interface BookingDetailsService {
	BookingDetails add(BookingDetails bookingDetails);

	BookingDetails update(BookingDetails bookingDetails)throws BookingDetailsNotFoundException;

	BookingDetails remove(int bookingId)throws BookingDetailsNotFoundException;

	List<BookingDetails> findAll();

	BookingDetails findById(int bookingId)throws BookingDetailsNotFoundException;
}
