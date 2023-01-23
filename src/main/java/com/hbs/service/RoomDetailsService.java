package com.hbs.service;

import java.util.List;

import com.hbs.entities.RoomDetails;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.RoomDetailsNotFoundException;

public interface RoomDetailsService {

	RoomDetails add(RoomDetails roomDetails) throws HotelNotFoundException, RoomDetailsNotFoundException;

	RoomDetails update(RoomDetails roomDetails) throws RoomDetailsNotFoundException, HotelNotFoundException;

	RoomDetails removeById(int roomDetailsId) throws RoomDetailsNotFoundException;

	List<RoomDetails> findAll();

	RoomDetails findById(int roomDetailsId) throws RoomDetailsNotFoundException;

	List<RoomDetails> findByHotelId(int hotelId);
}
