package com.hbs.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.hbs.dto.RoomDetailsDTO;
import com.hbs.dto.RoomImage;
import com.hbs.exceptions.ActiveBookingFoundException;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.InvalidImageFormatException;
import com.hbs.exceptions.RoomDetailsNotFoundException;

public interface RoomDetailsService {

	RoomDetailsDTO add(RoomDetailsDTO dto) throws HotelNotFoundException, RoomDetailsNotFoundException;

	RoomDetailsDTO uploadImage(RoomImage image) throws RoomDetailsNotFoundException, InvalidImageFormatException, IOException;
	
	RoomDetailsDTO update(RoomDetailsDTO dto) throws RoomDetailsNotFoundException, HotelNotFoundException;

	RoomDetailsDTO remove(int roomDetailsId) throws RoomDetailsNotFoundException, ActiveBookingFoundException, HotelNotFoundException;

	List<RoomDetailsDTO> findAll();

	RoomDetailsDTO findById(int roomDetailsId) throws RoomDetailsNotFoundException;

	List<RoomDetailsDTO> findByHotelId(int hotelId);
	
	List<RoomDetailsDTO> findAllAvailableRoom(LocalDate from, LocalDate to);

}
