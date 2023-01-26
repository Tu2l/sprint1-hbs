package com.hbs.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbs.dto.HotelDTO;
import com.hbs.entities.Hotel;
import com.hbs.exceptions.ActiveBookingFoundException;
import com.hbs.exceptions.HotelAlreadyExistsExcetion;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.InvalidEmailFormatException;
import com.hbs.exceptions.InvalidMobileNumberFormatException;
import com.hbs.repository.BookingDetailsRepository;
import com.hbs.repository.HotelRepository;
import com.hbs.repository.RoomDetailsRepository;
import com.hbs.service.HotelService;
import com.hbs.util.MapperUtil;
import com.hbs.util.ValidationUtil;

@Service
public class HotelServiceImpl implements HotelService {
	private static final String HOTEL_NOT_FOUND = "No Hotel found with id: ";
	private static final String INVALID_EMAIL_FORMAT = "Invalid email: ";
	private static final String INVALID_MOBILE_NUMBER_FORMAT = "Invalid mobile number";
	private static final String HOTEL_ALREADY_EXISTS = "Hotel already exists with email: ";
	private static final String ACTIVE_BOOKING_FOUND_MESSAGE = "Active booking found";

	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private RoomDetailsRepository roomRepository;
	@Autowired
	private BookingDetailsRepository bookingRepository;

	private void validateHotel(HotelDTO hotel) throws InvalidEmailFormatException, InvalidMobileNumberFormatException {
		if (!ValidationUtil.validateEmail(hotel.getEmail()))
			throw new InvalidEmailFormatException(INVALID_EMAIL_FORMAT + hotel.getEmail());
		boolean phone1 = ValidationUtil.validatePhoneNumber(hotel.getPhone1());
		boolean phone2 = !(hotel.getPhone2() != null || !hotel.getPhone2().isEmpty())
				|| ValidationUtil.validatePhoneNumber(hotel.getPhone2());
		if (!phone1 || !phone2)
			throw new InvalidMobileNumberFormatException(INVALID_MOBILE_NUMBER_FORMAT);
	}

	@Override
	public HotelDTO add(HotelDTO dto)
			throws InvalidEmailFormatException, InvalidMobileNumberFormatException, HotelAlreadyExistsExcetion {
		validateHotel(dto);
		if (hotelRepository.findByEmail(dto.getEmail()) != null)
			throw new HotelAlreadyExistsExcetion(HOTEL_ALREADY_EXISTS + dto.getEmail());
		return MapperUtil.mapToHotelDto(hotelRepository.save(MapperUtil.mapToHotel(dto)));
	}

	@Override
	public HotelDTO update(HotelDTO dto) throws HotelNotFoundException, InvalidEmailFormatException,
			InvalidMobileNumberFormatException, HotelAlreadyExistsExcetion {
		HotelDTO find = findById(dto.getHotelId());
		validateHotel(dto);

		if (!dto.getEmail().equalsIgnoreCase(find.getEmail()) && hotelRepository.findByEmail(dto.getEmail()) != null)
			throw new HotelAlreadyExistsExcetion(HOTEL_ALREADY_EXISTS + dto.getEmail());

		return MapperUtil.mapToHotelDto(hotelRepository.save(MapperUtil.mapToHotel(dto)));
	}

	@Override
	public HotelDTO remove(int id) throws HotelNotFoundException, ActiveBookingFoundException {
		HotelDTO dto = findById(id);

		if (bookingRepository.findByDateAndHotelIdCount(LocalDate.now(), id) > 0)
			throw new ActiveBookingFoundException(ACTIVE_BOOKING_FOUND_MESSAGE);

		roomRepository.deleteAll(roomRepository.findByHotelId(id));

		hotelRepository.deleteById(id);

		return dto;
	}

	@Override
	public List<HotelDTO> findAll() {
		return MapperUtil.mapToHotelDtoList(hotelRepository.findAll());
	}

	@Override
	public HotelDTO findById(int id) throws HotelNotFoundException {
		Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new HotelNotFoundException(HOTEL_NOT_FOUND + id));
		return MapperUtil.mapToHotelDto(hotel);

	}

}
