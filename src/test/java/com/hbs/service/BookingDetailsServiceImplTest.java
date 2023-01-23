package com.hbs.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hbs.entities.BookingDetails;
import com.hbs.entities.Hotel;
import com.hbs.entities.Payments;
import com.hbs.entities.RoomDetails;
import com.hbs.entities.User;
import com.hbs.exceptions.BookingDetailsNotFoundException;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.RoomDetailsNotFoundException;
import com.hbs.exceptions.UserNotFoundException;
import com.hbs.repository.BookingDetailsRepository;

@ExtendWith(MockitoExtension.class)
class BookingDetailsServiceImplTest {

	private static final String EXCEPTION = "Booking details not found wth Id: ";

	@Mock
	private BookingDetailsRepository bookingRepoMock;

	@InjectMocks
	private BookingDetailsServiceImpl serviceImplMock;

	private BookingDetails bookingDetails;

	private List<BookingDetails> allBookings = new ArrayList<>();

	private List<Payments> payments = new ArrayList<>();

	private List<RoomDetails> roomsList = new ArrayList<>();

	@BeforeEach
	public void setup() {
		User user = new User();
		user.setUserId(1);
		user.setUserName("kumar");
		Hotel hotel = new Hotel();
		hotel.setHotelId(1);
		hotel.setHotelName("Grand Hotel");
		bookingDetails = new BookingDetails();
		bookingDetails.setBookingId(1);
		bookingDetails.setUser(user);
		bookingDetails.setHotel(hotel);
		bookingDetails.setBookedFrom(LocalDate.of(2023, 1, 21));
		bookingDetails.setBookedTo(LocalDate.of(2023, 1, 25));
		bookingDetails.setNoOfAdults(2);
		bookingDetails.setNoOfChildren(1);
		bookingDetails.setAmount(1000);
		bookingDetails.setPaymentList(payments);
		bookingDetails.setRoomList(roomsList);
		// MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAdd() throws UserNotFoundException, HotelNotFoundException, RoomDetailsNotFoundException {
		when(serviceImplMock.add(bookingDetails)).thenReturn(bookingDetails);
		assertEquals(bookingDetails, serviceImplMock.add(bookingDetails));
	}

	@Test
	void testUpdate() throws UserNotFoundException, HotelNotFoundException, RoomDetailsNotFoundException {
		
		try {
			System.out.println(bookingDetails.getAmount());
			bookingDetails.setAmount(19000);
			
			when(serviceImplMock.update(bookingDetails)).thenReturn(bookingDetails);
			
			System.out.println(bookingDetails.getAmount());
			assertEquals(1000, bookingDetails.getAmount());
			verify(serviceImplMock).findById(bookingDetails.getBookingId()).getAmount();
		} catch (BookingDetailsNotFoundException e) {
			
		}
		
	}

	@Test
	void testDelete() {
		BookingDetails deleted;
		try {
			deleted = serviceImplMock.remove(bookingDetails.getBookingId());
			assertEquals(bookingDetails, deleted);
			verify(bookingRepoMock, times(1)).deleteById(bookingDetails.getBookingId());
		} catch (BookingDetailsNotFoundException e) {
			
		}
	}

	@Test
	void testFindAll() {
		allBookings = Arrays.asList(bookingDetails);
		when(serviceImplMock.findAll()).thenReturn(allBookings);
		assertEquals(allBookings, serviceImplMock.findAll());
		verify(bookingRepoMock).findAll();
	}

	@Test
	void testFindById() throws BookingDetailsNotFoundException {
		when(bookingRepoMock.findById(bookingDetails.getBookingId())).thenReturn(Optional.of(bookingDetails));
		BookingDetails search = serviceImplMock.findById(bookingDetails.getBookingId());
		assertEquals(bookingDetails, search);
		verify(bookingRepoMock).findById(bookingDetails.getBookingId());
	}

	@Test
	void testUpdate_throwsBookingDetailsNotFoundException() throws UserNotFoundException, HotelNotFoundException, RoomDetailsNotFoundException {
		try {
			when(serviceImplMock.update(bookingDetails))
					.thenThrow(new BookingDetailsNotFoundException(EXCEPTION + bookingDetails.getBookingId()));
			assertThrows(BookingDetailsNotFoundException.class, () -> serviceImplMock.update(bookingDetails));
		} catch (BookingDetailsNotFoundException e) {
		}
	}

	@Test
	void testFindById_throwsBookingDetailsNotFoundException() {
		try {
			when(serviceImplMock.findById(bookingDetails.getBookingId()))
					.thenThrow(new BookingDetailsNotFoundException(EXCEPTION + bookingDetails.getBookingId()));
			assertThrows(BookingDetailsNotFoundException.class,
					() -> serviceImplMock.findById(bookingDetails.getBookingId()));
		} catch (BookingDetailsNotFoundException e) {
		}

	}

}
