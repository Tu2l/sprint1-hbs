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
import com.hbs.exceptions.BookingDetailsNotFoundException;
import com.hbs.service.BookingDetailsService;
import com.hbs.util.MapperUtil;

@RestController
@RequestMapping("/bookingDetails")
public class BookingDetailsController {

	@Autowired
	private BookingDetailsService bookingDetailsService;

	@PostMapping
	public ResponseEntity<BookingDetailsDTO> add(@Valid @RequestBody BookingDetailsDTO bookingDetailsDto) {
		return new ResponseEntity<>(
				MapperUtil.mapToBookingDetailsDto(
						bookingDetailsService.add(MapperUtil.mapToBookingDetails(bookingDetailsDto))),
				HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<BookingDetailsDTO> update(@Valid @RequestBody BookingDetailsDTO bookingDetailsDto)
			throws BookingDetailsNotFoundException {
		return new ResponseEntity<>(
				MapperUtil.mapToBookingDetailsDto(
						bookingDetailsService.update(MapperUtil.mapToBookingDetails(bookingDetailsDto))),
				HttpStatus.OK);
	}

	@DeleteMapping("/{bookingId}")
	public ResponseEntity<BookingDetailsDTO> remove(@PathVariable int bookingId)
			throws BookingDetailsNotFoundException {
		return new ResponseEntity<>(MapperUtil.mapToBookingDetailsDto(bookingDetailsService.remove(bookingId)),
				HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<BookingDetailsDTO>> findAll() {
		return new ResponseEntity<>(MapperUtil.mapToBookingDetailsDtoList(bookingDetailsService.findAll()),
				HttpStatus.OK);
	}

	@GetMapping("/{bookingId}")
	public ResponseEntity<BookingDetailsDTO> findById(@PathVariable int bookingId)
			throws BookingDetailsNotFoundException {
		return new ResponseEntity<>(MapperUtil.mapToBookingDetailsDto(bookingDetailsService.findById(bookingId)),
				HttpStatus.OK);
	}
}
