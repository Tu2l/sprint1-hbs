package com.hbs.service;

import java.util.List;

import com.hbs.entities.BookingDetails;
import com.hbs.exceptions.BookingDetailsNotFoundException;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.RoomDetailsNotFoundException;
import com.hbs.exceptions.UserNotFoundException;

public interface BookingDetailsService {
	BookingDetails add(BookingDetails bookingDetails)
			throws UserNotFoundException, HotelNotFoundException, RoomDetailsNotFoundException;

	BookingDetails update(BookingDetails bookingDetails) throws BookingDetailsNotFoundException, UserNotFoundException,
			HotelNotFoundException, RoomDetailsNotFoundException;

	BookingDetails remove(int bookingId) throws BookingDetailsNotFoundException;

	List<BookingDetails> findAll();

	BookingDetails findById(int bookingId) throws BookingDetailsNotFoundException;
}
