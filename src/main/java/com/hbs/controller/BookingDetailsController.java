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
import com.hbs.entities.BookingDetails;
import com.hbs.exceptions.BookingDetailsNotFoundException;
import com.hbs.service.BookingDetailsService;

@RestController
@RequestMapping("/bookingDetails")
public class BookingDetailsController {

	@Autowired
	private BookingDetailsService bookingDetailsService;

	//http://localhost:8080/bookingDetails
	@PostMapping
	public ResponseEntity<BookingDetails> addBookingDetails(@RequestBody BookingDetails bookingDetails) {
		BookingDetails addedBookingDetails = bookingDetailsService.addBookingDetails(bookingDetails);
		return new ResponseEntity<>(addedBookingDetails, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<BookingDetails> updateBookingDetails(@RequestBody BookingDetails bookingDetails)
			throws BookingDetailsNotFoundException {
		BookingDetails updatedBookingDetails = bookingDetailsService.updateBookingDetails(bookingDetails);
		return new ResponseEntity<>(updatedBookingDetails, HttpStatus.OK);

	}

	@DeleteMapping("/{bookingId}")
	public ResponseEntity<Void> removeBookingDetails(@PathVariable int bookingId)
			throws BookingDetailsNotFoundException {
		BookingDetails bookingDetails = new BookingDetails();
		bookingDetails.setBookingId(bookingId);
		bookingDetailsService.removeBookingDetails(bookingDetails);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<BookingDetails>> showAllBookingDetails() {
		List<BookingDetails> bookingDetailsList = bookingDetailsService.showAllBookingDetails();
		return new ResponseEntity<>(bookingDetailsList, HttpStatus.OK);
	}

	@GetMapping("/{bookingId}")
	public ResponseEntity<BookingDetails> showBookingDetails(@PathVariable int bookingId)
			throws BookingDetailsNotFoundException {
		BookingDetails bookingDetails = bookingDetailsService.showBookingDetails(bookingId);
		return new ResponseEntity<>(bookingDetails, HttpStatus.FOUND);

	}
}
