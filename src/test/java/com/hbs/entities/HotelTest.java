package com.hbs.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HotelTest {
	
	Hotel hotel;
	
	@BeforeEach
	void setUp() {
		hotel = new Hotel();
		hotel.setHotelId(12);
		hotel.setHotelName("Sagar");
		hotel.setCity("Pune");
		hotel.setAddress("India");
		hotel.setAvgRatePerDay(1200);
		hotel.setDescription("Nice one");
		hotel.setEmail("sagar@gmail.com");
		hotel.setPhone1("1234567890");
		hotel.setPhone2("0987654321");
		hotel.setWebsite("www.sagar.com");
	}

	@Test
	void testGetHotelId() {
		assertEquals(12, hotel.getHotelId());
	}

	@Test
	void testGetCity() {
		assertEquals("Pune",hotel.getCity());
	}

	@Test
	void testGetHotelName() {
		assertEquals("Sagar",hotel.getHotelName());
	}

	@Test
	void testGetAddress() {
		assertEquals("India",hotel.getAddress());
	}

	@Test
	void testGetDescription() {
		assertEquals("Nice one",hotel.getDescription());
	}

	@Test
	void testGetAvgRatePerDay() {
		assertEquals(1200,hotel.getAvgRatePerDay());
	}

	@Test
	void testGetEmail() {
		assertEquals("sagar@gmail.com",hotel.getEmail());
	}

	@Test
	void testGetPhone1() {
		assertEquals("1234567890",hotel.getPhone1());
	}

	@Test
	void testGetPhone2() {
		assertEquals("0987654321",hotel.getPhone2());
	}

	@Test
	void testGetWebsite() {
		assertEquals("www.sagar.com",hotel.getWebsite());	}

	/*@Test
	void testGetRoomList() {
		RoomDetails room = new RoomDetails();
		room.setRoomId(0);
		List<RoomDetails> roomList = new ArrayList<>();
		roomList.add(room);
		hotel.setRoomList(roomList);
		assertEquals(roomList, hotel.getRoomList());
	}*/

}
