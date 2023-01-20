package com.hbs.service;

import java.util.List;

import com.hbs.entities.Hotel;
import com.hbs.exceptions.HotelNotFoundException;


public interface HotelService {
	public Hotel addHotel(Hotel hotel);
	public Hotel updateHotel(Hotel hotel) throws HotelNotFoundException;
	public Hotel removeHotel(Hotel hotel)throws HotelNotFoundException;
	public List<Hotel> showAllHotels();
	public Hotel showHotel(int  id) throws HotelNotFoundException;
}
