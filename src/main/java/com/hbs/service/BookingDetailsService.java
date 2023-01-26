package com.hbs.service;

import java.util.List;

import com.hbs.dto.BookingDetailsDTO;
import com.hbs.exceptions.ActiveBookingFoundException;
import com.hbs.exceptions.BookingDetailsNotFoundException;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.RoomAlreadyBookedException;
import com.hbs.exceptions.RoomDetailsNotFoundException;
import com.hbs.exceptions.UserNotFoundException;

public interface BookingDetailsService {
	BookingDetailsDTO add(BookingDetailsDTO bookingDetails) throws UserNotFoundException, HotelNotFoundException,
			RoomDetailsNotFoundException, RoomAlreadyBookedException;

	BookingDetailsDTO update(BookingDetailsDTO bookingDetails)
			throws BookingDetailsNotFoundException, UserNotFoundException, HotelNotFoundException,
			RoomDetailsNotFoundException, RoomAlreadyBookedException;

	BookingDetailsDTO remove(int bookingId) throws BookingDetailsNotFoundException, ActiveBookingFoundException;
	
	BookingDetailsDTO removeActive(int bookingId) throws BookingDetailsNotFoundException;

	List<BookingDetailsDTO> findAll();

	BookingDetailsDTO findById(int bookingId) throws BookingDetailsNotFoundException;
	
	List<BookingDetailsDTO> findByUserId(int userId) throws UserNotFoundException;
}
