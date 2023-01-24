package com.hbs.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hbs.dto.BookingDetailsDTO;
import com.hbs.entities.BookingDetails;
import com.hbs.entities.Payments;
import com.hbs.entities.RoomDetails;
import com.hbs.exceptions.BookingDetailsNotFoundException;
import com.hbs.exceptions.HotelNotFoundException;
import com.hbs.exceptions.RoomDetailsNotFoundException;
import com.hbs.exceptions.UserNotFoundException;
import com.hbs.repository.BookingDetailsRepository;
import com.hbs.repository.HotelRepository;
import com.hbs.repository.PaymentRepository;
import com.hbs.repository.RoomDetailsRepository;
import com.hbs.repository.UserRepository;
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
		bookingDetailsDTO.setAmount(1000.0);
		bookingDetailsDTO.setRoomIds(new ArrayList<>());
		bookingDetailsDTO.setPayments(new ArrayList<>());

		serviceImplMock = new BookingDetailsServiceImpl();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAddBookingDetailsSuccess() throws Exception {
		when(userRepoMock.existsById(bookingDetailsDTO.getUserId())).thenReturn(true);
		when(hotelRepoMock.existsById(bookingDetailsDTO.getHotelId())).thenReturn(true);
//	    when(roomRepoMock.existsById(any(Integer.class))).thenReturn(true);
		when(bookingRepoMock.save(any(BookingDetails.class)))
				.thenReturn(MapperUtil.mapToBookingDetails(bookingDetailsDTO));
		BookingDetailsDTO result = serviceImplMock.add(bookingDetailsDTO);

		assertNotNull(result);

		System.out.println("Expected: " + bookingDetailsDTO);
		System.out.println("Actual: " + result);
		assertEquals(bookingDetailsDTO, result);
		verify(bookingRepoMock, times(1)).save(any(BookingDetails.class));
	}

	@Test
	void testAdd() throws UserNotFoundException, HotelNotFoundException, RoomDetailsNotFoundException {
		when(userRepoMock.existsById(1)).thenReturn(true);
		when(hotelRepoMock.existsById(2)).thenReturn(true);
		when(roomRepoMock.findByRoomIdAndHotelIdCount(1, Arrays.asList(121))).thenReturn(1);
		assertEquals(bookingDetailsDTO, serviceImplMock.add(bookingDetailsDTO));
	}

	@Test
	void testUpdate() throws UserNotFoundException, HotelNotFoundException, RoomDetailsNotFoundException {

		try {
			bookingDetailsDTO.setAmount(19000);
			when(serviceImplMock.update(bookingDetailsDTO)).thenReturn(bookingDetailsDTO);
			assertEquals(1000, bookingDetailsDTO.getAmount());
			verify(serviceImplMock).findById(bookingDetailsDTO.getBookingId()).getAmount();
		} catch (BookingDetailsNotFoundException e) {

		}
	}

	@Test
	void testDelete() {
		BookingDetailsDTO deleted;
		try {
			deleted = serviceImplMock.remove(bookingDetailsDTO.getBookingId());
			assertEquals(bookingDetailsDTO, deleted);
			verify(bookingRepoMock, times(1)).deleteById(bookingDetailsDTO.getBookingId());
		} catch (BookingDetailsNotFoundException e) {

		}
	}

	@Test
	void testFindAll() {
		allBookings = Arrays.asList(bookingDetailsDTO);
		when(serviceImplMock.findAll()).thenReturn(allBookings);
		assertEquals(allBookings, serviceImplMock.findAll());
		verify(bookingRepoMock).findAll();
	}

	@Test
	void testFindById() throws BookingDetailsNotFoundException {
		// when(bookingRepoMock.findById(bookingDetailsDTO.getBookingId())).thenReturn(bookingDetailsDTO);
		BookingDetailsDTO search = serviceImplMock.findById(bookingDetailsDTO.getBookingId());
		assertEquals(bookingDetailsDTO, search);
		assertEquals(1, bookingDetailsDTO.getBookingId());
		verify(bookingRepoMock).findById(bookingDetailsDTO.getBookingId());
	}

	@Test
	void testUpdateThrowsBookingDetailsNotFoundException()
			throws UserNotFoundException, HotelNotFoundException, RoomDetailsNotFoundException {
		try {
			when(serviceImplMock.update(bookingDetailsDTO))
					.thenThrow(new BookingDetailsNotFoundException(EXCEPTION + bookingDetailsDTO.getBookingId()));
			assertThrows(BookingDetailsNotFoundException.class, () -> serviceImplMock.update(bookingDetailsDTO));
		} catch (BookingDetailsNotFoundException e) {
		}
	}

	@Test
	void testFindByIdThrowsBookingDetailsNotFoundException() {
		try {
			when(serviceImplMock.findById(bookingDetailsDTO.getBookingId()))
					.thenThrow(new BookingDetailsNotFoundException(EXCEPTION + bookingDetailsDTO.getBookingId()));
			assertThrows(BookingDetailsNotFoundException.class,
					() -> serviceImplMock.findById(bookingDetailsDTO.getBookingId()));
		} catch (BookingDetailsNotFoundException e) {
		}

	}

}
