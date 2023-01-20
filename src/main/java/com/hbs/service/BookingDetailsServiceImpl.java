package com.hbs.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hbs.entities.BookingDetails;
import com.hbs.exceptions.BookingDetailsNotFoundException;
import com.hbs.repository.IBookingDetailsRepository;

@Service
public class BookingDetailsServiceImpl implements BookingDetailsService {

	private static final String MESSAGE = "Booking details not found for booking id: ";

	@Autowired
	private IBookingDetailsRepository bookingDetailsRepository;

	@Override
	public BookingDetails addBookingDetails(BookingDetails bookingDetails) {

		return bookingDetailsRepository.save(bookingDetails);
	}

	@Override
	public BookingDetails updateBookingDetails(BookingDetails bookingDetails) throws BookingDetailsNotFoundException {
		bookingDetailsRepository.findById(bookingDetails.getBookingId())
				.orElseThrow(() -> new BookingDetailsNotFoundException(MESSAGE + bookingDetails.getBookingId()));
		return bookingDetailsRepository.save(bookingDetails);

	}

	@Override
	public BookingDetails removeBookingDetails(BookingDetails bookingDetails) throws BookingDetailsNotFoundException {

		return bookingDetailsRepository.findById(bookingDetails.getBookingId()).map(bd -> {
			bookingDetailsRepository.delete(bookingDetails);
			return bd;
		}).orElseThrow(() -> new BookingDetailsNotFoundException(MESSAGE + bookingDetails.getBookingId()));
	}

	@Override
	public List<BookingDetails> showAllBookingDetails() {
		return bookingDetailsRepository.findAll();
	}

	@Override
	public BookingDetails showBookingDetails(int bookingDetailsId) throws BookingDetailsNotFoundException {
		return bookingDetailsRepository.findById(bookingDetailsId)
				.orElseThrow(() -> new BookingDetailsNotFoundException(MESSAGE + bookingDetailsId));
	}

}
