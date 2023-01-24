package com.hbs.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoomDetailsDTOTest {
	
	private RoomDetailsDTO dto;
	
	@BeforeEach
	void setup() {
		dto = new RoomDetailsDTO();
		dto.setHotelId(546);
		dto.setRoomId(987);
		dto.setRoomNo("403");
		dto.setRoomType("Ac Room");
		dto.setRatePerDay(700);
		dto.setImageUrl("http://image/");
		
	}

	@Test
	void testGetHotelId() {
		assertEquals(546, dto.getHotelId());
	}

	@Test
	void testGetRoomId() {
		assertEquals(987, dto.getRoomId());
	}

	@Test
	void testGetRoomNo() {
		assertEquals("403", dto.getRoomNo());
	}

	@Test
	void testGetRoomType() {
		assertEquals("Ac Room", dto.getRoomType());
	}

	@Test
	void testGetRatePerDay() {
		assertEquals(700, dto.getRatePerDay());
	}

	@Test
	void testGetImageUrl() {
		assertEquals("http://image/", dto.getImageUrl());
	}
	
	//negative tests
	@Test
	void testGetHotelIdNegative() {
		assertNotEquals(547, dto.getHotelId());
	}

	

}
