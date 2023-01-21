package com.hbs.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.hbs.dto.HotelDTO;
import com.hbs.entities.Hotel;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.service.HotelService;
import com.hbs.util.MapperUtil;

@RestController
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	HotelService hotelService;

	@PostMapping
	public ResponseEntity<HotelDTO> add(@Valid @RequestBody HotelDTO hotelDto){
		Hotel newHotel = hotelService.add(MapperUtil.mapToHotel(hotelDto));
		return new ResponseEntity<>(MapperUtil.mapToHotelDto(newHotel), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<HotelDTO> update(@Valid @RequestBody HotelDTO hotelDto) throws HotelNotFoundException {
		Hotel updateHotel = hotelService.update(MapperUtil.mapToHotel(hotelDto));
		return new ResponseEntity<>(MapperUtil.mapToHotelDto(updateHotel), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HotelDTO> remove(@PathVariable int id) throws HotelNotFoundException {
		Hotel removeHotel = hotelService.remove(id);
		return new ResponseEntity<>(MapperUtil.mapToHotelDto(removeHotel), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<HotelDTO>> findAll() throws HotelNotFoundException{
		List<Hotel> showAllHotel = hotelService.findAll();
		return new ResponseEntity<>(MapperUtil.mapToHotelList(showAllHotel), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<HotelDTO> findById(@PathVariable int id) throws HotelNotFoundException {
		Hotel showHotel;
		showHotel = hotelService.findById(id);
		return new ResponseEntity<>(MapperUtil.mapToHotelDto(showHotel), HttpStatus.OK);
	}
}
