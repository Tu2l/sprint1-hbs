package com.hbs.repository;

import java.util.List;

import com.hbs.entities.*;

public interface IHotelRepository {
	public Hotel addHotel(Hotel hotel);
	public Hotel updateHotel(Hotel hotel);
	public Hotel removeHotel(Hotel hotel);
	public List<Hotel> showAllHotels();
	public Hotel showHotel(Hotel id);
}
