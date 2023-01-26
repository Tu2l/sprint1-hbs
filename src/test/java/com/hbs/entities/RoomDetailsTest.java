package com.hbs.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoomDetailsTest {
	
	RoomDetails room;
	Hotel hotel;
	
	@BeforeEach
	void setUp() {
		room = new RoomDetails();
		hotel = new Hotel();
		room.setImageUrl("path");
		room.setRatePerDay(1300);
		room.setRoomId(123);
		room.setRoomNo("309");
		room.setRoomType("Delux");
		room.setHotel(hotel);
	}

	
	@Test
	void testGetRoomId() {
		assertEquals(123, room.getRoomId());
	}

	@Test
	void testGetHotel() {
		assertEquals(0, hotel.getHotelId());
	}

	@Test
	void testGetRoomNo() {
		assertEquals("309",room.getRoomNo());
	}

	@Test
	void testGetRoomType() {
		assertEquals("Delux",room.getRoomType());
	}

	@Test
	void testGetRatePerDay() {
		assertEquals(1300,room.getRatePerDay());
	}

//	@Test
//	void testIsAvailable() {
//		assertTrue(false,room.isAvailable());
//	}

	@Test
	void testGetImageUrl() {
		assertEquals("path", room.getImageUrl());
	}

}
