package com.hbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbs.entities.Hotel;
import com.hbs.exceptions.HotelAlreadyExistsExcetion;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.InvalidEmailFormatException;
import com.hbs.exceptions.InvalidMobileNumberFormatException;
import com.hbs.repository.HotelRepository;
import com.hbs.util.ValidationUtil;

@Service
public class HotelServiceImpl implements HotelService {
	private static final String HOTEL_NOT_FOUND = "No Hotel found with id: ";
	private static final String INVALID_EMAIL_FORMAT = "Invalid email: ";
	private static final String INVALID_MOBILE_NUMBER_FORMAT = "Invalid mobile number";
	private static final String HOTEL_ALREADY_EXISTS = "Hotel already exists with email: ";

	@Autowired
	HotelRepository hotelRepository;

	private void validateHotel(Hotel hotel) throws InvalidEmailFormatException, InvalidMobileNumberFormatException {
		if (!ValidationUtil.validateEmail(hotel.getEmail()))
			throw new InvalidEmailFormatException(INVALID_EMAIL_FORMAT + hotel.getEmail());

		boolean phone1 = ValidationUtil.validatePhoneNumber(hotel.getPhone1());

		boolean phone2 = !(hotel.getPhone2() != null || !hotel.getPhone2().isEmpty())
				|| ValidationUtil.validatePhoneNumber(hotel.getPhone2());

		if (!phone1 || !phone2)
			throw new InvalidMobileNumberFormatException(INVALID_MOBILE_NUMBER_FORMAT);

	}

	@Override
	public Hotel add(Hotel hotel)
			throws InvalidEmailFormatException, InvalidMobileNumberFormatException, HotelAlreadyExistsExcetion {

		validateHotel(hotel);

		if (hotelRepository.findByEmail(hotel.getEmail()) != null)
			throw new HotelAlreadyExistsExcetion(HOTEL_ALREADY_EXISTS + hotel.getEmail());

		return hotelRepository.save(hotel);
	}

	@Override
	public Hotel update(Hotel hotel) throws HotelNotFoundException, InvalidEmailFormatException,
			InvalidMobileNumberFormatException, HotelAlreadyExistsExcetion {

		Hotel find = findById(hotel.getHotelId());

		validateHotel(hotel);

		if (!hotel.getEmail().equalsIgnoreCase(find.getEmail())
				&& hotelRepository.findByEmail(hotel.getEmail()) != null)
			throw new HotelAlreadyExistsExcetion(HOTEL_ALREADY_EXISTS + hotel.getEmail());

		return hotelRepository.save(hotel);
	}

	@Override
	public Hotel remove(int id) throws HotelNotFoundException {
		Hotel hotel = findById(id);
		hotelRepository.deleteById(id);
		return hotel;
	}

	@Override
	public List<Hotel> findAll() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel findById(int id) throws HotelNotFoundException {
		return hotelRepository.findById(id).orElseThrow(() -> new HotelNotFoundException(HOTEL_NOT_FOUND + id));
	}

}
