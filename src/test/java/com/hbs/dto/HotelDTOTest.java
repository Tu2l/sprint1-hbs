package com.hbs.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HotelDTOTest {

	private HotelDTO dto;
	
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
	}

	@Test
	void testGetHotelId() {
	assertEquals(1, dto.getHotelId());
	}

	@Test
	void testGetCity() {
	assertEquals("Delhi", dto.getCity());
	}

	@Test
	void testGetHotelName() {
	assertEquals("Chandra",dto.getHotelName());
	}

	@Test
	void testGetAddress() {
	assertEquals("India",dto.getAddress());
	}

	@Test
	void testGetDescription() {
	assertEquals("Good one", dto.getDescription());
	}

	@Test
	void testGetAvgRatePerDay() {
	assertEquals(1500, dto.getAvgRatePerDay());
	}

	@Test
	void testGetEmail() {
	assertEquals("ch@gmail.com", dto.getEmail());
	}

	@Test
	void testGetPhone1() {
	assertEquals("1234567890", dto.getPhone1());
	}

	@Test
	void testGetPhone2() {
	assertEquals("2345678901", dto.getPhone2());
	}

	@Test
	void testGetWebsite() {
	assertEquals("www.ch.com", dto.getWebsite());

	}
}
