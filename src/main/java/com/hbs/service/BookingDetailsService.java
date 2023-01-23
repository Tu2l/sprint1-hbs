package com.hbs.service;

import java.util.List;

import com.hbs.dto.BookingDetailsDTO;
import com.hbs.entities.BookingDetails;
import com.hbs.exceptions.BookingDetailsNotFoundException;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.RoomDetailsNotFoundException;
import com.hbs.exceptions.UserNotFoundException;

public interface BookingDetailsService {
	BookingDetailsDTO add(BookingDetailsDTO bookingDetails)
			throws UserNotFoundException, HotelNotFoundException, RoomDetailsNotFoundException;

	BookingDetailsDTO update(BookingDetailsDTO bookingDetails) throws BookingDetailsNotFoundException,
			UserNotFoundException, HotelNotFoundException, RoomDetailsNotFoundException;

	BookingDetailsDTO remove(int bookingId) throws BookingDetailsNotFoundException;

	List<BookingDetailsDTO> findAll();

	BookingDetailsDTO findById(int bookingId) throws BookingDetailsNotFoundException;
}
