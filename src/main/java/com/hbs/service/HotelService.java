package com.hbs.service;

import java.util.List;

import com.hbs.entities.Hotel;
import com.hbs.exceptions.HotelNotFoundException;


public interface HotelService {
	public Hotel add(Hotel hotel);
	public Hotel update(Hotel hotel) throws HotelNotFoundException;
	public Hotel remove(int id)throws HotelNotFoundException;
	public List<Hotel> findAll();
	public Hotel findById(int  id) throws HotelNotFoundException;
}
