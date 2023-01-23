package com.hbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbs.dto.BookingDetailsDTO;
import com.hbs.entities.BookingDetails;
import com.hbs.exceptions.BookingDetailsNotFoundException;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.RoomDetailsNotFoundException;
import com.hbs.exceptions.UserNotFoundException;
import com.hbs.repository.BookingDetailsRepository;
import com.hbs.repository.HotelRepository;
import com.hbs.repository.RoomDetailsRepository;
import com.hbs.repository.UserRepository;
import com.hbs.util.MapperUtil;

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

	private void validateBooking(BookingDetailsDTO dto)
			throws UserNotFoundException, HotelNotFoundException, RoomDetailsNotFoundException {
		// validate user, hotel, and room
		if (!userRepository.existsById(dto.getUserId()))
			throw new UserNotFoundException(USER_NOT_FOUND + dto.getUserId());

		if (!hotelRepository.existsById(dto.getHotelId()))
			throw new HotelNotFoundException(HOTEL_NOT_FOUND + dto.getHotelId());

//		if (!dto.getRoomIds().stream().allMatch(roomRepository::existsById)) 
//			throw new RoomDetailsNotFoundException(ROOM_DETAILS_NOT_FOUND);


		// validate booking dates also
	}

	@Override
	public BookingDetailsDTO add(BookingDetailsDTO dto)
			throws UserNotFoundException, HotelNotFoundException, RoomDetailsNotFoundException {
		validateBooking(dto);
		return MapperUtil.mapToBookingDetailsDto(bookingDetailsRepository.save(MapperUtil.mapToBookingDetails(dto)));
	}

	@Override
	public BookingDetailsDTO update(BookingDetailsDTO dto) throws BookingDetailsNotFoundException,
			UserNotFoundException, HotelNotFoundException, RoomDetailsNotFoundException {
		findById(dto.getBookingId());
		validateBooking(dto);
		BookingDetails bookingDetails = MapperUtil.mapToBookingDetails(dto);
		bookingDetails = bookingDetailsRepository.save(bookingDetails);
		return MapperUtil.mapToBookingDetailsDto(bookingDetails);
	}

	@Override
	public BookingDetailsDTO remove(int bookingId) throws BookingDetailsNotFoundException {
		BookingDetailsDTO dto = findById(bookingId);
		bookingDetailsRepository.deleteById(bookingId);
		return dto;
	}

	@Override
	public List<BookingDetailsDTO> findAll() {
		return MapperUtil.mapToBookingDetailsDtoList(bookingDetailsRepository.findAll());

	}

	@Override
	public BookingDetailsDTO findById(int bookingId) throws BookingDetailsNotFoundException {
		return MapperUtil.mapToBookingDetailsDto(bookingDetailsRepository.findById(bookingId)
				.orElseThrow(() -> new BookingDetailsNotFoundException(BOOKING_DETAILS_NOT_FOUND + bookingId)));
	}

}
