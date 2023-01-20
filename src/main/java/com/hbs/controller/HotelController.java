package com.hbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbs.entities.Hotel;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	HotelService hotelService;

	@PostMapping
	public ResponseEntity<Hotel> add(@RequestBody Hotel hotel) {
		Hotel newHotel = hotelService.addHotel(hotel);
		return new ResponseEntity<>(newHotel, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Hotel> update(@RequestBody Hotel hotel) throws HotelNotFoundException {
		Hotel updateHotel = hotelService.updateHotel(hotel);
		return new ResponseEntity<>(updateHotel, HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<Hotel> remove(@RequestBody Hotel hotel) throws HotelNotFoundException {
		Hotel removeHotel = hotelService.removeHotel(hotel);
		return new ResponseEntity<>(removeHotel, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Hotel>> showAll(@RequestBody Hotel hotel) {
		List<Hotel> showAllHotel = hotelService.showAllHotels();
		return new ResponseEntity<>(showAllHotel, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Hotel> showHotel(@PathVariable int id) throws HotelNotFoundException {
		Hotel showHotel;
		showHotel = hotelService.showHotel(id);
		return new ResponseEntity<>(showHotel, HttpStatus.OK);
	}
}
