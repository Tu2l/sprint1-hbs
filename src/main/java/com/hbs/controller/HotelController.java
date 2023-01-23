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
import com.hbs.exceptions.HotelAlreadyExistsExcetion;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.InvalidEmailFormatException;
import com.hbs.exceptions.InvalidMobileNumberFormatException;
import com.hbs.service.HotelService;
import com.hbs.util.MapperUtil;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	@Autowired
	HotelService hotelService;

	@PostMapping
	public ResponseEntity<HotelDTO> add(@Valid @RequestBody HotelDTO dto)
			throws InvalidEmailFormatException, InvalidMobileNumberFormatException, HotelAlreadyExistsExcetion {
		//Hotel newHotel = hotelService.add(MapperUtil.mapToHotel(hotelDto));
		return new ResponseEntity<>(hotelService.add(dto), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<HotelDTO> update(@Valid @RequestBody HotelDTO dto, @PathVariable int id)
			throws HotelNotFoundException, InvalidEmailFormatException, InvalidMobileNumberFormatException,
			HotelAlreadyExistsExcetion {
		
//		Hotel updateHotel = hotelService.update(MapperUtil.mapToHotel(hotelDto));
//		updateHotel.setHotelId(id);
		
		return new ResponseEntity<>(hotelService.update(dto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HotelDTO> remove(@PathVariable int id) throws HotelNotFoundException {
		//Hotel removeHotel = hotelService.remove(id);
		return new ResponseEntity<>(hotelService.remove(id), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<HotelDTO>> findAll() {
		//List<Hotel> showAllHotel = hotelService.findAll();
		return new ResponseEntity<>(hotelService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<HotelDTO> findById(@PathVariable int id) throws HotelNotFoundException {
	//	Hotel showHotel = hotelService.findById(id);
		return new ResponseEntity<>(hotelService.findById(id), HttpStatus.OK);
	}
}
