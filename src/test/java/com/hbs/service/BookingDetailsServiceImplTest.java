package com.hbs.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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

import com.hbs.dto.BookingDetailsDTO;
import com.hbs.entities.BookingDetails;
import com.hbs.exceptions.ActiveBookingFoundException;
import com.hbs.exceptions.BookingDetailsNotFoundException;
import com.hbs.repository.BookingDetailsRepository;
import com.hbs.repository.HotelRepository;
import com.hbs.repository.PaymentRepository;
import com.hbs.repository.RoomDetailsRepository;
import com.hbs.repository.UserRepository;
import com.hbs.serviceimpl.BookingDetailsServiceImpl;
import com.hbs.util.MapperUtil;

@ExtendWith(MockitoExtension.class)
class BookingDetailsServiceImplTest {

	private static final String EXCEPTION = "Booking details not found wth Id: ";

	@Mock
	private UserRepository userRepoMock;

	@Mock
	private HotelRepository hotelRepoMock;

	@Mock
	private RoomDetailsRepository roomRepoMock;

	@Mock
	private BookingDetailsRepository bookingRepoMock;

	@Mock
	private PaymentRepository payRepoMock;

	@InjectMocks
	private BookingDetailsServiceImpl serviceImplMock;

	private BookingDetailsDTO bookingDetailsDTO;

	private List<BookingDetailsDTO> allBookings = new ArrayList<>();

	@BeforeEach
	public void setUp() {
		bookingDetailsDTO = new BookingDetailsDTO();
		bookingDetailsDTO.setBookingId(1);
		bookingDetailsDTO.setUserId(1);
		bookingDetailsDTO.setHotelId(2);
		bookingDetailsDTO.setBookedFrom(LocalDate.now());
		bookingDetailsDTO.setBookedTo(LocalDate.now().plusDays(1));
		bookingDetailsDTO.setNoOfAdults(2);
		bookingDetailsDTO.setNoOfChildren(1);
		bookingDetailsDTO.setAmount(0.0);
		bookingDetailsDTO.setRoomIds(Arrays.asList(1,2));
		bookingDetailsDTO.setPayments(Arrays.asList(100.0,200.0));
		serviceImplMock = new BookingDetailsServiceImpl();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAdd() throws Exception {
		when(userRepoMock.existsById(bookingDetailsDTO.getUserId())).thenReturn(true);
		when(hotelRepoMock.existsById(bookingDetailsDTO.getHotelId())).thenReturn(true);
		when(bookingRepoMock.save(any(BookingDetails.class)))
				.thenReturn(MapperUtil.mapToBookingDetails(bookingDetailsDTO));
		BookingDetailsDTO result = serviceImplMock.add(bookingDetailsDTO);
		assertNotNull(result);
		assertEquals(bookingDetailsDTO, result);
		verify(bookingRepoMock, times(1)).save(any(BookingDetails.class));
	}

	@Test
	void testRemove() throws BookingDetailsNotFoundException, ActiveBookingFoundException {
	    int bookingId = 1;
	    BookingDetailsDTO dto = new BookingDetailsDTO();
	    dto.setBookingId(bookingId);
	    when(bookingRepoMock.findById(bookingId)).thenReturn(Optional.of(MapperUtil.mapToBookingDetails(dto)));
	    BookingDetailsDTO removedDto = serviceImplMock.remove(bookingId);   
	    assertEquals(removedDto.getBookingId(), bookingId);
	    verify(bookingRepoMock, times(1)).deleteById(bookingId);
	}

	@Test
	void testFindAll() {
	    
	    BookingDetailsDTO dto1 = new BookingDetailsDTO();
	    dto1.setBookingId(1);
	    BookingDetailsDTO dto2 = new BookingDetailsDTO();
	    dto2.setBookingId(2);
	    when(bookingRepoMock.findAll()).thenReturn(Arrays.asList(MapperUtil.mapToBookingDetails(dto1), MapperUtil.mapToBookingDetails(dto2)));
	    List<BookingDetailsDTO> allBookings = serviceImplMock.findAll();	    
	    assertEquals(2, allBookings.size());
	    assertEquals(1, allBookings.get(0).getBookingId());
	    assertEquals(2, allBookings.get(1).getBookingId());
	}
}
