package com.hbs.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hbs.dto.HotelDTO;
import com.hbs.entities.Hotel;
import com.hbs.exceptions.ActiveBookingFoundException;
import com.hbs.exceptions.HotelAlreadyExistsExcetion;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.InvalidEmailFormatException;
import com.hbs.exceptions.InvalidMobileNumberFormatException;
import com.hbs.service.HotelService;
import com.hbs.util.MapperUtil;

@ExtendWith(MockitoExtension.class)
class HotelControllerTest {

	@Mock
	private HotelService hotelService;

	@InjectMocks
	private HotelController hotelController;

	private HotelDTO hotelDto;
	private Hotel hotel;
	private List<Hotel> hotels;
	private List<HotelDTO> hotelDtos;

	@BeforeEach
	void setUp() {
		hotelDto = new HotelDTO();
		hotelDto.setHotelId(1);
		hotelDto.setCity("nandyal");
		hotelDto.setEmail("maurya.in@hotel.com");
		hotelDto.setPhone1("080-54376");
		hotelDto.setHotelName("maurya");
		hotels = new ArrayList<>();
		hotels.add(hotel);
		hotelDtos = MapperUtil.mapToHotelDtoList(hotels);
	}

	@Test
	void add() throws InvalidEmailFormatException, InvalidMobileNumberFormatException, HotelAlreadyExistsExcetion {
		when(hotelService.add(hotelDto)).thenReturn(hotelDto);
		ResponseEntity<HotelDTO> response = hotelController.add(hotelDto);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(hotelDto, response.getBody());
	}

	@Test
	void update() throws HotelNotFoundException, InvalidEmailFormatException, InvalidMobileNumberFormatException, HotelAlreadyExistsExcetion {
		when(hotelService.update(hotelDto)).thenReturn(hotelDto);
		ResponseEntity<HotelDTO> response = hotelController.update(hotelDto,1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(hotelDto, response.getBody());
	}

	@Test
	void remove() throws HotelNotFoundException, ActiveBookingFoundException {
		when(hotelService.remove(1)).thenReturn(hotelDto);
		ResponseEntity<HotelDTO> response = hotelController.remove(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("nandyal", response.getBody().getCity());
	}

	@Test
	void findAll() {
		when(hotelService.findAll()).thenReturn(hotelDtos);
		ResponseEntity<List<HotelDTO>> response = hotelController.findAll();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(hotelDtos, response.getBody());
	}

	@Test
	void findById() throws HotelNotFoundException {
		when(hotelService.findById(1)).thenReturn(hotelDto);
		ResponseEntity<HotelDTO> response = hotelController.findById(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(hotelDto, response.getBody());
	}

	@Test
	void removeThrowsHotelNotFoundException() throws HotelNotFoundException, ActiveBookingFoundException {
		when(hotelService.remove(1)).thenThrow(HotelNotFoundException.class);
		assertThrows(HotelNotFoundException.class, () -> hotelController.remove(1));
	}

	@Test
	void findByIdThrowsHotelNotFoundException() throws HotelNotFoundException {
		when(hotelService.findById(1)).thenThrow(HotelNotFoundException.class);
		assertThrows(HotelNotFoundException.class, () -> hotelController.findById(1));
	}

	@Test
	void updateThrowsHotelNotFoundException() throws HotelNotFoundException, InvalidEmailFormatException, InvalidMobileNumberFormatException, HotelAlreadyExistsExcetion {
		when(hotelService.update(hotelDto)).thenThrow(HotelNotFoundException.class);
		assertThrows(HotelNotFoundException.class, () -> hotelController.update(hotelDto,1));
	}

}
