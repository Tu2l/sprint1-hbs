package com.hbs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbs.entities.Hotel;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.repository.IHotelRepository;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	IHotelRepository hotelRepository;
	private static final String HOTEL_NOT_FOUND = "No Hotel found with id: ";

	@Override
	public Hotel add(Hotel hotel) {
		hotelRepository.save(hotel);
		return null;
	}

	@Override
	public Hotel update(Hotel hotel) throws HotelNotFoundException {
		findById(hotel.getHotelId());
		hotelRepository.save(hotel);
		return hotel;
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
		return hotelRepository.findById(id).orElseThrow(()->new HotelNotFoundException(HOTEL_NOT_FOUND +id));
	}

}
