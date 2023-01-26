package com.hbs.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookingDetailsTest {

	BookingDetails bookingDetails;
	Hotel hotel;
	User user;

	@BeforeEach
	void setUp() {
		hotel = new Hotel();
		user = new User();
		bookingDetails = new BookingDetails();
		bookingDetails.setBookingId(121);
		bookingDetails.setAmount(1500);
		bookingDetails.setBookedFrom(LocalDate.of(2023, 3, 5));
		bookingDetails.setBookedTo(LocalDate.of(2023, 3, 7));
		bookingDetails.setHotel(hotel);
		bookingDetails.setNoOfAdults(2);
		bookingDetails.setNoOfChildren(23);
		bookingDetails.setUser(user);
	}

	@Test
	void testGetBookingId() {
		assertEquals(121, bookingDetails.getBookingId());
	}

	@Test
	void testGetUser() {
		assertEquals(0, user.getUserId());
	}

	@Test
	void testGetHotel() {
		// System.out.println();

		assertEquals(0, hotel.getHotelId());
	}

	@Test
	void testGetBookedFrom() {
		assertEquals(LocalDate.of(2023, 3, 5), bookingDetails.getBookedFrom());
	}

	@Test
	void testGetBookedTo() {
		assertEquals(LocalDate.of(2023, 3, 7), bookingDetails.getBookedTo());
	}

	@Test
	void testGetNoOfAdults() {
		assertEquals(2, bookingDetails.getNoOfAdults());
	}

	@Test
	void testGetNoOfChildren() {
		assertEquals(23, bookingDetails.getNoOfChildren());
	}

	@Test
	void testGetAmount() {
		assertEquals(1500, bookingDetails.getAmount());
	}

	@Test
	void testGetRoomList() {
		RoomDetails room = new RoomDetails();
		room.setRoomId(0);
		List<RoomDetails> roomList = new ArrayList<>();
		roomList.add(room);
		bookingDetails.setRoomList(roomList);
		assertEquals(roomList, bookingDetails.getRoomList());
	}

	@Test
	void testGetPaymentList() {
		Payments payment1 = new Payments();
		payment1.setAmount(100);
		Payments payment2 = new Payments();
		payment2.setAmount(200);
		List<Payments> paymentList = new ArrayList<>();
		paymentList.add(payment1);
		paymentList.add(payment2);
		bookingDetails.setPaymentList(paymentList);
		assertEquals(paymentList, bookingDetails.getPaymentList());
		assertEquals(bookingDetails, payment1.getBookingDetails());
		assertEquals(bookingDetails, payment2.getBookingDetails());
		assertEquals(100, payment1.getTransaction().getAmount());
		assertEquals(200, payment2.getTransaction().getAmount());

	}

}
