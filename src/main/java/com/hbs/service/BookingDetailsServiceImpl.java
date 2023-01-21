package com.hbs.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hbs.entities.BookingDetails;
import com.hbs.exceptions.BookingDetailsNotFoundException;
import com.hbs.repository.BookingDetailsRepository;

@Service
public class BookingDetailsServiceImpl implements BookingDetailsService {

	private static final String NO_BOOKING_DETAILS = "Booking details not found for booking id: ";

	@Autowired
	private BookingDetailsRepository bookingDetailsRepository;

	@Override
	public BookingDetails add(BookingDetails bookingDetails) {

		return bookingDetailsRepository.save(bookingDetails);
	}

	@Override
	public BookingDetails update(BookingDetails bookingDetails) throws BookingDetailsNotFoundException {
		bookingDetailsRepository.findById(bookingDetails.getBookingId())
				.orElseThrow(() -> new BookingDetailsNotFoundException(NO_BOOKING_DETAILS + bookingDetails.getBookingId()));
		return bookingDetailsRepository.save(bookingDetails);

	}

	@Override
	public BookingDetails remove(int bookingId) throws BookingDetailsNotFoundException {
		BookingDetails bookingDetails = findById(bookingId);
		bookingDetailsRepository.deleteById(bookingId);
		return bookingDetails;	
	}

	@Override
	public List<BookingDetails> findAll() {
		return bookingDetailsRepository.findAll();
	}

	@Override
	public BookingDetails findById(int bookingDetailsId) throws BookingDetailsNotFoundException {
		return bookingDetailsRepository.findById(bookingDetailsId)
				.orElseThrow(() -> new BookingDetailsNotFoundException(NO_BOOKING_DETAILS + bookingDetailsId));
	}

}
