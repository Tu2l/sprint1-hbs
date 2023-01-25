package com.hbs.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookingDetailsDTOTest {

	private BookingDetailsDTO dto;

	@BeforeEach
	public void setUp() {
		dto = new BookingDetailsDTO();
		dto.setBookingId(1);
		dto.setUserId(1);
		dto.setHotelId(2);
		dto.setBookedFrom(LocalDate.now());
		dto.setBookedTo(LocalDate.now().plusDays(1));
		dto.setNoOfAdults(2);
		dto.setNoOfChildren(1);
		dto.setAmount(1000.0);
		dto.setRoomIds(new ArrayList<>());
		dto.setPayments(new ArrayList<>());
	}

	@Test
	void testGetBookingId() {
		assertEquals(1, dto.getBookingId());
	}

	@Test
	void testGetUserId() {
		assertEquals(1, dto.getUserId());
	}

	@Test
	void testGetHotelId() {
		assertEquals(2, dto.getHotelId());
	}

	@Test
	void testGetNoOfAdults() {
		assertEquals(2, dto.getNoOfAdults());
	}

	@Test
	void testGetNoOfChildren() {
		assertEquals(1, dto.getNoOfChildren());
	}

	@Test
	void testGetAmount() {
		assertEquals(1000.0, dto.getAmount());
	}

	@Test
	void testGetRoomIds() {
		assertNotNull(dto.getRoomIds());
		assertTrue(dto.getRoomIds().isEmpty());
	}

	@Test
	void testGetPayments() {
		assertNotNull(dto.getPayments());
		assertTrue(dto.getPayments().isEmpty());
	}
	
	//negative testscenarios
	@Test
	void testGetBookingIdNegative() {
		assertNotEquals(3, dto.getBookingId());
	}
	
	@Test
	void testGetUserIdNegative() {
		assertNotEquals(2, dto.getUserId());
	}

	@Test
	void testGetHotelIdNegative() {
		assertNotEquals(4, dto.getHotelId());
	}

	@Test
	void testGetNoOfAdultsNegative() {
		assertNotEquals(3, dto.getNoOfAdults());
	}

	@Test
	void testGetNoOfChildrenNegative() {
		assertNotEquals(2, dto.getNoOfChildren());
	}

	@Test
	void testGetAmountNegative() {
		assertNotEquals(500.0, dto.getAmount());
	}

	@Test
	void testGetRoomIdsNegative() {
		assertNotNull(dto.getRoomIds());
		assertTrue(dto.getRoomIds().isEmpty());
	}

	@Test
	void testGetPaymentsNegative() {
		assertNotNull(dto.getPayments());
		assertTrue(dto.getPayments().isEmpty());
	}

}
