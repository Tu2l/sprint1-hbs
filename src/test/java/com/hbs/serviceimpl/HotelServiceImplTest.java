package com.hbs.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hbs.dto.HotelDTO;
import com.hbs.entities.Hotel;
import com.hbs.repository.BookingDetailsRepository;
import com.hbs.repository.HotelRepository;
import com.hbs.repository.RoomDetailsRepository;
import com.hbs.util.MapperUtil;

@ExtendWith(MockitoExtension.class)
class HotelServiceImplTest {

	@Mock
	private HotelRepository hotelRepository;
	@Mock
	private RoomDetailsRepository roomRepo;
	@Mock
	private BookingDetailsRepository bookingRepository;

	@InjectMocks
	private HotelServiceImpl hotelService;
	private MockedStatic<MapperUtil> mockedUtil;

	private HotelDTO dto;
	private List<HotelDTO> hotels = new ArrayList<>();
	private Hotel hotel;
	
	@BeforeEach
	void setUp() {
		dto = new HotelDTO();
		dto.setHotelId(1);
		dto.setCity("Delhi");
		dto.setHotelName("Chandra");
		dto.setEmail("ch@gmail.com");
		dto.setDescription("Good one");
		dto.setAddress("India");
		dto.setAvgRatePerDay(1500);
		dto.setPhone1("1234567890");
		dto.setPhone2("2345678901");
		dto.setWebsite("www.ch.com");
		
		hotels.add(dto);
		hotel = MapperUtil.mapToHotel(dto);
	}
	
	@AfterEach
	void close() {
		mockedUtil.close();
	}

	@Test
	void testAdd() {
		try {
			when(MapperUtil.mapToHotel(dto)).thenReturn(hotel);
			when(MapperUtil.mapToHotelDto(hotel)).thenReturn(dto);
			when(hotelService.add(dto)).thenReturn(dto);
			
			assertEquals(dto, hotelService.add(dto));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	void testFindById() {
		fail("Not yet implemented");
	}
}