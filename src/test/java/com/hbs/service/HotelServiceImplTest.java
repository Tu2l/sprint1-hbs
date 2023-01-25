package com.hbs.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.hbs.dto.HotelDTO;
import com.hbs.exceptions.ActiveBookingFoundException;
import com.hbs.exceptions.HotelAlreadyExistsExcetion;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.InvalidEmailFormatException;
import com.hbs.exceptions.InvalidMobileNumberFormatException;
import com.hbs.repository.HotelRepository;
import com.hbs.repository.RoomDetailsRepository;
import com.hbs.serviceimpl.HotelServiceImpl;
import com.hbs.util.MapperUtil;

@ExtendWith(MockitoExtension.class)
class HotelServiceImplTest {

	private static final String EXCEPTION = "Hotel not found with id:";

	@Mock
	private HotelRepository hotelRepository;

	@Mock
	private RoomDetailsRepository roomRepo;

	@Mock
	ModelMapper mapper;

	@InjectMocks
	private HotelServiceImpl hotelService;

	private HotelDTO hotel;

	List<HotelDTO> hotels = new ArrayList<>();

	@BeforeEach
	void setUp() {
		hotel = new HotelDTO();
		hotel.setHotelId(1);
		hotel.setCity("Delhi");
		hotel.setHotelName("Chandra");
		hotel.setEmail("ch@gmail.com");
		hotel.setDescription("Good one");
		hotel.setAddress("India");
		hotel.setAvgRatePerDay(1500);
		hotel.setPhone1("1234567890");
		hotel.setPhone2("2345678901");
		hotel.setWebsite("www.ch.com");
	}

	@Test
	void testAdd() throws InvalidEmailFormatException, InvalidMobileNumberFormatException, HotelAlreadyExistsExcetion {
		mockStatic(MapperUtil.class);
		when(hotelService.add(hotel)).thenReturn(hotel);
		assertEquals(hotel, hotelService.add(hotel));
	}

	@Test
	void testRemove() throws HotelNotFoundException, ActiveBookingFoundException {
		when(hotelService.remove(1)).thenReturn(hotel);
		verify(hotelRepository, times(1)).delete(MapperUtil.mapToHotel(hotel));
	}

	@Test
	void testfindAll() {
		hotels = Arrays.asList(hotel);
		when(hotelService.findAll()).thenReturn(hotels);
		assertEquals(1, hotelService.findAll().size());
	}

	@Test
	void testFindByIdThrowsHotelNotFoundException() {
		try {
			when(hotelService.findById(1)).thenThrow(new HotelNotFoundException(EXCEPTION + hotel.getHotelId()));
			assertThrows(HotelNotFoundException.class, () -> hotelService.findById(hotel.getHotelId()));
		} catch (HotelNotFoundException e) {
		}
	}
}