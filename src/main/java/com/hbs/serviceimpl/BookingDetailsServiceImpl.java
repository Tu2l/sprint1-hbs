package com.hbs.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbs.dto.BookingDetailsDTO;
import com.hbs.entities.BookingDetails;
import com.hbs.exceptions.ActiveBookingFoundException;
import com.hbs.exceptions.BookingDetailsNotFoundException;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.RoomAlreadyBookedException;
import com.hbs.exceptions.RoomDetailsNotFoundException;
import com.hbs.exceptions.UserNotFoundException;
import com.hbs.repository.BookingDetailsRepository;
import com.hbs.repository.HotelRepository;
import com.hbs.repository.RoomDetailsRepository;
import com.hbs.repository.UserRepository;
import com.hbs.service.BookingDetailsService;
import com.hbs.util.MapperUtil;

@Service
public class BookingDetailsServiceImpl implements BookingDetailsService {
	private static final String BOOKING_DETAILS_NOT_FOUND_MESSAGE = "Booking details not found for bookingid: ";
	private static final String USER_NOT_FOUND_MESSAGE = "User not found for userid: ";
	private static final String HOTEL_NOT_FOUND_MESSAGE = "Hotel not found for hotelid: ";
	private static final String ROOM_DETAILS_NOT_FOUND_MESSAGE = "Invalid RoomDetails";
	private static final String ROOM_ALREADY_BOOKED_MESSAGE = "Room already booked";
	private static final String ACTIVE_BOOKING_FOUND_MESSAGE = "Active booking found";

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
		// validate user,hotel,room
		if (!userRepository.existsById(dto.getUserId()))
			throw new UserNotFoundException(USER_NOT_FOUND_MESSAGE + dto.getUserId());

		if (!hotelRepository.existsById(dto.getHotelId()))
			throw new HotelNotFoundException(HOTEL_NOT_FOUND_MESSAGE + dto.getHotelId());

		if (roomRepository.findByRoomIdAndHotelIdCount(dto.getHotelId(), dto.getRoomIds()) != dto.getRoomIds().size())
			throw new RoomDetailsNotFoundException(ROOM_DETAILS_NOT_FOUND_MESSAGE);

	}

	private void checkForAvailability(BookingDetailsDTO dto) throws RoomAlreadyBookedException {
		// validate booking dates also
		int size = roomRepository.findBookedRoomsCountByDateAndRoomId(dto.getBookedFromDate(), dto.getBookedToDate(),
				dto.getRoomIds());
		if (size != 0)
			throw new RoomAlreadyBookedException(ROOM_ALREADY_BOOKED_MESSAGE);

	}

	@Override
	public BookingDetailsDTO add(BookingDetailsDTO dto) throws UserNotFoundException, HotelNotFoundException,
			RoomDetailsNotFoundException, RoomAlreadyBookedException {

		validateBooking(dto);
		checkForAvailability(dto);

		// calculate amount
		dto.setAmount(roomRepository.calculateTotalAmount(dto.getRoomIds()));
		BookingDetails booking = bookingDetailsRepository.save(MapperUtil.mapToBookingDetails(dto));

		return MapperUtil.mapToBookingDetailsDto(booking);
	}

	@Override
	public BookingDetailsDTO update(BookingDetailsDTO dto) throws BookingDetailsNotFoundException,
			UserNotFoundException, HotelNotFoundException, RoomDetailsNotFoundException, RoomAlreadyBookedException {

		validateBooking(dto);

		bookingDetailsRepository.deleteById(dto.getBookingId());
		
		BookingDetails bookingDetails = MapperUtil.mapToBookingDetails(dto);

		try {
			checkForAvailability(dto);
		} catch (RoomAlreadyBookedException ex) {
			throw new RoomAlreadyBookedException(ex.getMessage());
		} finally {
			bookingDetails.setBookingId(dto.getBookingId());
			dto.setAmount(roomRepository.calculateTotalAmount(dto.getRoomIds()));
			bookingDetails = bookingDetailsRepository.save(bookingDetails);
		}

		return MapperUtil.mapToBookingDetailsDto(bookingDetails);
	}

	@Override
	public BookingDetailsDTO remove(int bookingId) throws BookingDetailsNotFoundException, ActiveBookingFoundException {
		BookingDetailsDTO dto = findById(bookingId);
		
		if(bookingDetailsRepository.findCountByDate(LocalDate.now(),bookingId) > 0)
			throw new ActiveBookingFoundException(ACTIVE_BOOKING_FOUND_MESSAGE);

		bookingDetailsRepository.deleteById(bookingId);

		return dto;
	}

	
	@Override
	public BookingDetailsDTO removeActive(int bookingId) throws BookingDetailsNotFoundException {
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
				.orElseThrow(() -> new BookingDetailsNotFoundException(BOOKING_DETAILS_NOT_FOUND_MESSAGE + bookingId)));
	}

	@Override
	public List<BookingDetailsDTO> findByUserId(int userId) throws UserNotFoundException {
		if (!userRepository.existsById(userId))
			throw new UserNotFoundException(USER_NOT_FOUND_MESSAGE + userId);

		return  MapperUtil.mapToBookingDetailsDtoList(bookingDetailsRepository.findByUserId(userId));
	}
	

}
