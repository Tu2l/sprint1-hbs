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
	private static final String message = "No Hotel Found";

	@Override
	public Hotel addHotel(Hotel hotel) {
		hotelRepository.save(hotel);
		return null;
	}

	@Override
	public Hotel updateHotel(Hotel hotel) throws HotelNotFoundException {
		Hotel hHotel = hotelRepository.findById(hotel.getHotelId())
				.orElseThrow(() -> new HotelNotFoundException(message + hotel.getHotelId()));
		return hotelRepository.save(hotel);
	}

	@Override
	public Hotel removeHotel(Hotel hotel) throws HotelNotFoundException {
		Optional<Hotel> Hotelobj = hotelRepository.findById(hotel.getHotelId());
		Hotelobj.ifPresent(h -> hotelRepository.delete(hotel));
		return Hotelobj.orElseThrow(() -> new HotelNotFoundException(message + hotel.getHotelId()));
	}

	@Override
	public List<Hotel> showAllHotels() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel showHotel(int id) throws HotelNotFoundException {
		return hotelRepository.findById(id).get();
	}

}
