package com.hbs.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hbs.dto.BookingDetailsDTO;
import com.hbs.entities.Hotel;
import com.hbs.exceptions.ActiveBookingFoundException;
import com.hbs.exceptions.BookingDetailsNotFoundException;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.RoomAlreadyBookedException;
import com.hbs.exceptions.RoomDetailsNotFoundException;
import com.hbs.exceptions.UserNotFoundException;
import com.hbs.service.BookingDetailsService;

@ExtendWith(MockitoExtension.class)
class BookingDetailsControllerTest {

	@Mock
	private BookingDetailsService bookingDetailsService;


	@InjectMocks
	private BookingDetailsController bookingDetailsController;

	private BookingDetailsDTO bookingDetailsDto;
	
	Hotel hotel = new Hotel();
	@BeforeEach
	public void setup() {
		bookingDetailsDto = new BookingDetailsDTO();
		bookingDetailsDto.setBookingId(1);
		bookingDetailsDto.setUserId(1);		
		hotel.setHotelId(1);
		hotel.setHotelName("Maurya");
		bookingDetailsDto.setHotelId(1);
		bookingDetailsDto.setBookedFrom(LocalDate.now());
		bookingDetailsDto.setBookedTo(LocalDate.now().plusDays(3));
		bookingDetailsDto.setNoOfAdults(2);
		bookingDetailsDto.setNoOfChildren(1);
		bookingDetailsDto.setAmount(500.0);
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@Order(1)
	void testAdd() throws UserNotFoundException, HotelNotFoundException, RoomDetailsNotFoundException, RoomAlreadyBookedException {
		when(bookingDetailsService.add(bookingDetailsDto)).thenReturn(bookingDetailsDto);
		ResponseEntity<BookingDetailsDTO> response = bookingDetailsController.add(bookingDetailsDto);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(500.0, bookingDetailsDto.getAmount());
		assertEquals(bookingDetailsDto, response.getBody());
	}

	@Test
	@Order(4)
	void testUpdate() throws BookingDetailsNotFoundException, UserNotFoundException, HotelNotFoundException,
			RoomDetailsNotFoundException, RoomAlreadyBookedException {
		when(bookingDetailsService.update(bookingDetailsDto)).thenReturn(bookingDetailsDto);
		ResponseEntity<BookingDetailsDTO> response = bookingDetailsController.update(1,bookingDetailsDto);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(bookingDetailsDto, response.getBody());
	}

	@Test
	@Order(5)
	void testRemove() throws BookingDetailsNotFoundException, ActiveBookingFoundException {
		when(bookingDetailsService.remove(anyInt())).thenReturn(bookingDetailsDto);
		ResponseEntity<BookingDetailsDTO> response = bookingDetailsController.remove(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(bookingDetailsDto, response.getBody());
	}

	@Test
	@Order(3)
	void testFindAll() {
		List<BookingDetailsDTO> bookingDetailsList = new ArrayList<>();
		bookingDetailsList.add(bookingDetailsDto);
		when(bookingDetailsService.findAll()).thenReturn(bookingDetailsList);
		ResponseEntity<List<BookingDetailsDTO>> response = bookingDetailsController.findAll();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(bookingDetailsList.size(), response.getBody().size());
	}

	@Test
	@Order(2)
	void testFindById() throws BookingDetailsNotFoundException {
		when(bookingDetailsService.findById(anyInt())).thenReturn(bookingDetailsDto);
		ResponseEntity<BookingDetailsDTO> response = bookingDetailsController.findById(1);
		assertEquals(HttpStatus.FOUND, response.getStatusCode());
		assertEquals(bookingDetailsDto, response.getBody());
	}

	@Test
	void testAddNew() throws UserNotFoundException, HotelNotFoundException, RoomDetailsNotFoundException, RoomAlreadyBookedException {
		when(bookingDetailsService.add(bookingDetailsDto)).thenReturn(bookingDetailsDto);
		ResponseEntity<BookingDetailsDTO> response = bookingDetailsController.add(bookingDetailsDto);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
}
