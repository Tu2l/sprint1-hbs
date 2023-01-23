package com.hbs.service;

import java.util.List;

import com.hbs.dto.RoomDetailsDTO;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.RoomDetailsNotFoundException;

public interface RoomDetailsService {

	RoomDetailsDTO add(RoomDetailsDTO dto) throws HotelNotFoundException, RoomDetailsNotFoundException;

	RoomDetailsDTO update(RoomDetailsDTO dto) throws RoomDetailsNotFoundException, HotelNotFoundException;

	RoomDetailsDTO removeById(int roomDetailsId) throws RoomDetailsNotFoundException;

	List<RoomDetailsDTO> findAll();

	RoomDetailsDTO findById(int roomDetailsId) throws RoomDetailsNotFoundException;

	List<RoomDetailsDTO> findByHotelId(int hotelId);
}
