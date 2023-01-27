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
import com.hbs.exceptions.ActiveBookingFoundException;
import com.hbs.exceptions.BookingDetailsNotFoundException;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.RoomAlreadyBookedException;
import com.hbs.exceptions.RoomDetailsNotFoundException;
import com.hbs.exceptions.UserNotFoundException;
import com.hbs.service.BookingDetailsService;

@RestController
@RequestMapping("/bookings")
public class BookingDetailsController {

	@Autowired
	private BookingDetailsService bookingDetailsService;

	@PostMapping
	public ResponseEntity<BookingDetailsDTO> add(@Valid @RequestBody BookingDetailsDTO bookingDetailsDTO)
			throws UserNotFoundException, HotelNotFoundException, RoomDetailsNotFoundException,
			RoomAlreadyBookedException {
		return new ResponseEntity<>(bookingDetailsService.add(bookingDetailsDTO), HttpStatus.CREATED);
	}

	@PutMapping("/{bookingId}")
	public ResponseEntity<BookingDetailsDTO> update(@PathVariable int bookingId,
			@Valid @RequestBody BookingDetailsDTO bookingDetailsDTO) throws BookingDetailsNotFoundException,
			UserNotFoundException, HotelNotFoundException, RoomDetailsNotFoundException, RoomAlreadyBookedException {

		bookingDetailsDTO.setBookingId(bookingId);
		return new ResponseEntity<>(bookingDetailsService.update(bookingDetailsDTO), HttpStatus.OK);
	}

	@DeleteMapping("/{bookingId}")
	public ResponseEntity<BookingDetailsDTO> remove(@PathVariable int bookingId)
			throws BookingDetailsNotFoundException, ActiveBookingFoundException {
		return new ResponseEntity<>(bookingDetailsService.remove(bookingId), HttpStatus.OK);
	}

	@DeleteMapping("/force/{bookingId}")
	public ResponseEntity<BookingDetailsDTO> removeActive(@PathVariable int bookingId)
			throws BookingDetailsNotFoundException {
		return new ResponseEntity<>(bookingDetailsService.removeActive(bookingId), HttpStatus.OK);
	}

	@GetMapping("/{bookingId}")
	public ResponseEntity<BookingDetailsDTO> findById(@PathVariable int bookingId)
			throws BookingDetailsNotFoundException {
		return new ResponseEntity<>(bookingDetailsService.findById(bookingId), HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<BookingDetailsDTO>> findByUserId(@PathVariable int userId) throws UserNotFoundException {
		return new ResponseEntity<>(bookingDetailsService.findByUserId(userId), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<BookingDetailsDTO>> findAll() {
		return new ResponseEntity<>(bookingDetailsService.findAll(), HttpStatus.OK);
	}
}
