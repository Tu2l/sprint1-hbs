package com.hbs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbs.entities.BookingDetails;
import com.hbs.entities.RoomDetails;
import com.hbs.exceptions.BookingDetailsNotFoundException;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.RoomDetailsNotFoundException;
import com.hbs.exceptions.UserNotFoundException;
import com.hbs.repository.BookingDetailsRepository;
import com.hbs.repository.HotelRepository;
import com.hbs.repository.RoomDetailsRepository;
import com.hbs.repository.UserRepository;

@Service
public class BookingDetailsServiceImpl implements BookingDetailsService {
	private static final String BOOKING_DETAILS_NOT_FOUND = "Booking details not found for bookingid: ";
	private static final String USER_NOT_FOUND = "User not found for userid: ";
	private static final String HOTEL_NOT_FOUND = "Hotel not found for hotelid: ";
	private static final String ROOM_DETAILS_NOT_FOUND = "Invalid RoomDetails";

	@Autowired
	private BookingDetailsRepository bookingDetailsRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private RoomDetailsRepository roomRepository;

	private void validateBooking(BookingDetails booking)
			throws UserNotFoundException, HotelNotFoundException, RoomDetailsNotFoundException {
		// validate user,hotel,room
		if (!userRepository.existsById(booking.getBookingId()))
			throw new UserNotFoundException(USER_NOT_FOUND + booking.getBookingId());

		if (!hotelRepository.existsById(booking.getHotel().getHotelId()))
			throw new HotelNotFoundException(HOTEL_NOT_FOUND + booking.getHotel().getHotelId());

		List<Integer> roomIds = booking.getRoomList().stream().map(RoomDetails::getRoomId).collect(Collectors.toList());
		if (roomRepository.findByRoomIdAndHotelIdCount(booking.getHotel().getHotelId(), roomIds) != roomIds.size())
			throw new RoomDetailsNotFoundException(ROOM_DETAILS_NOT_FOUND);
	}

	@Override
	public BookingDetails add(BookingDetails booking)
			throws UserNotFoundException, HotelNotFoundException, RoomDetailsNotFoundException {

		validateBooking(booking);

		return bookingDetailsRepository.save(booking);
	}

	@Override
	public BookingDetails update(BookingDetails booking) throws BookingDetailsNotFoundException, UserNotFoundException,
			HotelNotFoundException, RoomDetailsNotFoundException {
		findById(booking.getBookingId());

		validateBooking(booking);

		return bookingDetailsRepository.save(booking);
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
				.orElseThrow(() -> new BookingDetailsNotFoundException(BOOKING_DETAILS_NOT_FOUND + bookingDetailsId));
	}

}
