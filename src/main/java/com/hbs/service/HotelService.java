package com.hbs.service;

import java.util.List;

import com.hbs.entities.Hotel;
import com.hbs.exceptions.HotelAlreadyExistsExcetion;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.InvalidEmailFormatException;
import com.hbs.exceptions.InvalidMobileNumberFormatException;


public interface HotelService {
	public Hotel add(Hotel hotel) throws InvalidEmailFormatException, InvalidMobileNumberFormatException, HotelAlreadyExistsExcetion;
	public Hotel update(Hotel hotel) throws HotelNotFoundException, InvalidEmailFormatException, InvalidMobileNumberFormatException, HotelAlreadyExistsExcetion;
	public Hotel remove(int id)throws HotelNotFoundException;
	public List<Hotel> findAll();
	public Hotel findById(int  id) throws HotelNotFoundException;
}
