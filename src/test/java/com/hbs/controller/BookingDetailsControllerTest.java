package com.hbs.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hbs.dto.BookingDetailsDTO;
import com.hbs.entities.BookingDetails;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.UserNotFoundException;
import com.hbs.service.BookingDetailsService;
import com.hbs.util.MapperUtil;

@ExtendWith(MockitoExtension.class)
public class BookingDetailsControllerTest {

	@Mock
	private BookingDetailsService bookingDetailsService;

	//private MapperUtil mapperUtil;

	@InjectMocks
	private BookingDetailsController bookingDetailsController;

	private BookingDetailsDTO bookingDetailsDto;
	private BookingDetails bookingDetails;

	@BeforeEach
	public void setup() {
		bookingDetailsDto = new BookingDetailsDTO();
		bookingDetails = new BookingDetails();

		MockitoAnnotations.openMocks(this);
	}

//	@Test
//	    public void testAdd() throws UserNotFoundException, HotelNotFoundException {
//	        when(MapperUtil.mapToBookingDetails(bookingDetailsDto)).thenReturn(bookingDetails);
//	        when(bookingDetailsService.add(bookingDetails)).thenReturn(bookingDetails);
//	        when(MapperUtil.mapToBookingDetailsDto(bookingDetails)).thenReturn(bookingDetailsDto);
//	        ResponseEntity<BookingDetailsDTO> response = bookingDetailsController.add(bookingDetailsDto);
//	        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//	    }
}


