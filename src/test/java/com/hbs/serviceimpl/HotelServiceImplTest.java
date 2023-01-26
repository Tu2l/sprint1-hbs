package com.hbs.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hbs.dto.HotelDTO;
import com.hbs.entities.Hotel;
import com.hbs.repository.BookingDetailsRepository;
import com.hbs.repository.HotelRepository;
import com.hbs.repository.RoomDetailsRepository;
import com.hbs.util.MapperUtil;
import com.hbs.util.ValidationUtil;

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
	
	private MockedStatic<MapperUtil> mockedMapperUtil;
	private MockedStatic<ValidationUtil> mockedValidationUtil;

	private HotelDTO dto;
	private List<HotelDTO> dtos = new ArrayList<>();
	private List<Hotel> hotels = new ArrayList<>();
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

		dtos.add(dto);
		hotel = MapperUtil.mapToHotel(dto);

		hotels.add(hotel);
	}

	@AfterEach
	void close() {
		if (mockedMapperUtil != null)
			mockedMapperUtil.close();
		if (mockedValidationUtil != null)
			mockedValidationUtil.close();
	}

	@Test
	void testAdd() {
		try {
			mockedMapperUtil = mockStatic(MapperUtil.class);
			mockedValidationUtil = mockStatic(ValidationUtil.class);
			when(ValidationUtil.validateEmail(anyString())).thenReturn(true);
			when(ValidationUtil.validatePhoneNumber(anyString())).thenReturn(true);
			when(ValidationUtil.validatePhoneNumber(anyString())).thenReturn(true);
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
		try {
			dto.setHotelName("new hotel");
			
			mockedMapperUtil = mockStatic(MapperUtil.class);
			mockedValidationUtil = mockStatic(ValidationUtil.class);
			when(ValidationUtil.validateEmail(anyString())).thenReturn(true);
			when(ValidationUtil.validatePhoneNumber(anyString())).thenReturn(true);
			when(ValidationUtil.validatePhoneNumber(anyString())).thenReturn(true);
			when(MapperUtil.mapToHotel(dto)).thenReturn(hotel);
			when(MapperUtil.mapToHotelDto(hotel)).thenReturn(dto);
			when(hotelRepository.findById(dto.getHotelId())).thenReturn(Optional.of(hotel));
			when(hotelService.update(dto)).thenReturn(dto);

			assertEquals(dto, hotelService.update(dto));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	void testRemove() {
		try {
			mockedMapperUtil = mockStatic(MapperUtil.class);
			when(MapperUtil.mapToHotelDto(hotel)).thenReturn(dto);
			when(hotelRepository.findById(dto.getHotelId())).thenReturn(Optional.of(hotel));

			Mockito.lenient().when(hotelRepository.findById(dto.getHotelId())).thenReturn(Optional.of(hotel))
					.then(i -> hotelService.remove(dto.getHotelId()));

			assertEquals(dto,  hotelService.remove(dto.getHotelId()));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testFindAll() {
		try {
			mockedMapperUtil = mockStatic(MapperUtil.class);
			when(MapperUtil.mapToHotelDtoList(hotels)).thenReturn(dtos);
			when(hotelRepository.findAll()).thenReturn(hotels);
			when(hotelService.findAll()).thenReturn(dtos);

			assertEquals(dtos, hotelService.findAll());
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	void testFindById() {
		try {
			mockedMapperUtil = mockStatic(MapperUtil.class);
			when(MapperUtil.mapToHotel(dto)).thenReturn(hotel);
			when(MapperUtil.mapToHotelDto(hotel)).thenReturn(dto);
			when(hotelRepository.findById(dto.getHotelId())).thenReturn(Optional.of(hotel));
			when(hotelService.findById(dto.getHotelId())).thenReturn(dto);

			assertEquals(dto, hotelService.findById(dto.getHotelId()));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}