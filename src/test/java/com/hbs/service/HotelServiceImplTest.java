package com.hbs.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.hbs.dto.HotelDTO;
import com.hbs.entities.Hotel;
import com.hbs.exceptions.HotelAlreadyExistsExcetion;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.InvalidEmailFormatException;
import com.hbs.exceptions.InvalidMobileNumberFormatException;
import com.hbs.repository.HotelRepository;
import com.hbs.util.MapperUtil;

@ExtendWith(MockitoExtension.class)
class HotelServiceImplTest {

	private static final String  EXCEPTION= "Hotel not found with id:";
	
	@Mock
	private HotelRepository hotelRepository;
	
	@Mock
	ModelMapper mapper;
	
	@InjectMocks
	private HotelServiceImpl hotelService;
	
	private HotelDTO hotel;
	
	List<HotelDTO> hotels = new ArrayList<>();
	
	@BeforeEach
	 void setUp() {
        //MockitoAnnotations.openMocks(this);
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
		Hotel newHotel = mapper.map(hotel,Hotel.class);
		when(mapper.map(newHotel,HotelDTO.class)).thenReturn(hotel);
	//	when(hotelRepository.save(newHotel)).thenReturn(newHotel);
		when(hotelService.add(hotel)).thenReturn(hotel);
		assertEquals(hotel, hotelService.add(hotel));
	}
	
/*	@Test
	void testUpdate() throws HotelNotFoundException{
		System.out.println(hotel.getAvgRatePerDay());
		when(serviceImplMock.update(hotel)).thenReturn(hotel);
		hotel.setAvgRatePerDay(1000.0);
		assertEquals(5000.0, hotel.getAvgRatePerDay());
		System.out.println(hotel.getAvgRatePerDay());
	}*/
	@Test
	  void testUpdate() throws HotelNotFoundException, InvalidEmailFormatException, InvalidMobileNumberFormatException, HotelAlreadyExistsExcetion {
	      //  when(hotelRepository.findById(1)).thenReturn(Optional.of(hotel));
	        when(hotelService.update(hotel)).thenReturn(hotel);
	        HotelDTO updatedHotel = hotelService.update(hotel);
	        assertEquals(hotel, updatedHotel);
	        verify(hotelRepository, times(1)).findById(1);
	      //  verify(hotelRepository, times(1)).save(hotel);
	    }
	
//	@Test
//	void testDelete() throws HotelNotFoundException{
//		
//			when(hotelRepository.findById(hotel.getHotelId())).thenReturn(Optional.of(hotel)).thenAnswer(i->hotelService.remove(hotel.getHotelId()));
//		assertEquals(hotel.getHotelId(),hotelService.findAll());
//		when(hotelRepository.findById(1)).thenReturn(Optional.of(hotel));
//		//when(serviceImplMock.findById(1)).thenReturn(hotel);
//		when(hotelService.remove(1)).thenReturn(hotel);
//		Hotel delete = hotelService.remove(hotel.getHotelId());
//		assertEquals(hotel, delete);
//		verify(hotelRepository, times(1)).deleteById(hotel.getHotelId());	
//	}
	@Test
	public void testRemove() throws HotelNotFoundException {
		//when(hotelRepository.findById(hotel.getHotelId())).thenReturn(Optional.of(hotel));
		when(hotelService.remove(hotel.getHotelId())).thenReturn(hotel);
		HotelDTO result = hotelService.remove(hotel.getHotelId());
		assertEquals(hotel, result);
	}
	
	@Test
	void testfindAll() {
		hotels = Arrays.asList(hotel);
		when(hotelService.findAll()).thenReturn(hotels);
		assertEquals(hotels, hotelService.findAll());
		verify(hotelRepository).findAll();
	}
	
	@Test
	void testFindById_ThrowsHotelNotFoundException(){
		try {
		    // System.err.println(hotel);
			when(hotelService.findById(1)).thenThrow(new HotelNotFoundException(EXCEPTION+hotel.getHotelId()));
			assertThrows(HotelNotFoundException.class, ()-> hotelService.findById(hotel.getHotelId()));
		}catch (HotelNotFoundException e) {
		}
	}
}
