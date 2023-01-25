package com.hbs.service;

import java.util.List;

import com.hbs.dto.HotelDTO;
import com.hbs.exceptions.ActiveBookingFoundException;
import com.hbs.exceptions.HotelAlreadyExistsExcetion;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.InvalidEmailFormatException;
import com.hbs.exceptions.InvalidMobileNumberFormatException;


public interface HotelService {
	public HotelDTO add(HotelDTO dto) throws InvalidEmailFormatException, InvalidMobileNumberFormatException, HotelAlreadyExistsExcetion;
	public HotelDTO update(HotelDTO dto) throws HotelNotFoundException, InvalidEmailFormatException, InvalidMobileNumberFormatException, HotelAlreadyExistsExcetion;
	public HotelDTO remove(int id)throws HotelNotFoundException, ActiveBookingFoundException;
	public List<HotelDTO> findAll();
	public HotelDTO findById(int  id) throws HotelNotFoundException;
}
