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

import com.hbs.dto.BookingDetailsDTO;
import com.hbs.entities.BookingDetails;
import com.hbs.exceptions.BookingDetailsNotFoundException;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.RoomDetailsNotFoundException;
import com.hbs.exceptions.UserNotFoundException;
import com.hbs.service.BookingDetailsService;
import com.hbs.util.MapperUtil;

@RestController
@RequestMapping("/booking")
public class BookingDetailsController {

	@Autowired
	private BookingDetailsService bookingDetailsService;

	@PostMapping
	public ResponseEntity<BookingDetailsDTO> add(@Valid @RequestBody BookingDetailsDTO bookingDto)
			throws UserNotFoundException, HotelNotFoundException, RoomDetailsNotFoundException {

		BookingDetails booking = MapperUtil.mapToBookingDetails(bookingDto);
		bookingDto = MapperUtil.mapToBookingDetailsDto(bookingDetailsService.add(booking));

		return new ResponseEntity<>(bookingDto, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<BookingDetailsDTO> update(@Valid @RequestBody BookingDetailsDTO bookingDto,@PathVariable int id)
			throws BookingDetailsNotFoundException, UserNotFoundException, HotelNotFoundException,
			RoomDetailsNotFoundException {
		
		BookingDetails booking = MapperUtil.mapToBookingDetails(bookingDto);
		booking.setBookingId(id);
		
		bookingDto = MapperUtil.mapToBookingDetailsDto(bookingDetailsService.update(booking));
		
		return new ResponseEntity<>(bookingDto, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<BookingDetailsDTO> remove(@PathVariable int id)
			throws BookingDetailsNotFoundException {
		return new ResponseEntity<>(MapperUtil.mapToBookingDetailsDto(bookingDetailsService.remove(id)),
				HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<BookingDetailsDTO>> findAll() {
		return new ResponseEntity<>(MapperUtil.mapToBookingDetailsDtoList(bookingDetailsService.findAll()),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BookingDetailsDTO> findById(@PathVariable int id)
			throws BookingDetailsNotFoundException {
		return new ResponseEntity<>(MapperUtil.mapToBookingDetailsDto(bookingDetailsService.findById(id)),
				HttpStatus.OK);
	}
}
