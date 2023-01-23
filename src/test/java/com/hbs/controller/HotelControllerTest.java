package com.hbs.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HotelControllerTest {
/*
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
		hotel = MapperUtil.mapToHotel(hotelDto);
		hotels = new ArrayList<>();
		hotels.add(hotel);
		hotelDtos = MapperUtil.mapToHotelList(hotels);
	}

	@Test
	void add() throws InvalidEmailFormatException, InvalidMobileNumberFormatException, HotelAlreadyExistsExcetion {
		when(hotelService.add(hotel)).thenReturn(hotel);
		ResponseEntity<HotelDTO> response = hotelController.add(hotelDto);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(hotelDto, response.getBody());
	}

	@Test
	void update() throws HotelNotFoundException, InvalidEmailFormatException, InvalidMobileNumberFormatException, HotelAlreadyExistsExcetion {
		when(hotelService.update(hotel)).thenReturn(hotel);
		ResponseEntity<HotelDTO> response = hotelController.update(hotelDto,1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(hotelDto, response.getBody());
	}

	@Test
	void remove() throws HotelNotFoundException {
		when(hotelService.remove(1)).thenReturn(hotel);
		ResponseEntity<HotelDTO> response = hotelController.remove(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("nandyala", response.getBody().getCity());
	}

	@Test
	void findAll() {
		when(hotelService.findAll()).thenReturn(hotels);
		ResponseEntity<List<HotelDTO>> response = hotelController.findAll();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(hotelDtos, response.getBody());
	}

//	@Test
//	void findById() throws HotelNotFoundException {
//		when(hotelService.findById(1)).thenReturn(hotel);
//		ResponseEntity<HotelDTO> response = hotelController.findById(1);
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//		assertEquals(hotelDto, response.getBody());
//	}

	@Test
	void removeThrowsHotelNotFoundException() throws HotelNotFoundException {
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
		when(hotelService.update(hotel)).thenThrow(HotelNotFoundException.class);
		assertThrows(HotelNotFoundException.class, () -> hotelController.update(hotelDto,1));
	}
*/
}
