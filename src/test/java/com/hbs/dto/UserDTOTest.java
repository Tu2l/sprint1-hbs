package com.hbs.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserDTOTest {
	
	
	private UserDTO dto;
	
	@BeforeEach
	void setup() {
		dto = new UserDTO();
		dto.setUserId(101);
		dto.setUsername("raj");
		dto.setEmail("mk@gmail.com");
		dto.setPassword("root");
		dto.setMobile("9876543210");
		dto.setAddress("Madurai");
	}

	@Test
	void testGetUserId() {
		assertEquals(101, dto.getUserId());
	}

	@Test
	void testGetUsername() {
		assertEquals("raj", dto.getUsername());
	}

	@Test
	void testGetEmail() {
		assertEquals("mk@gmail.com", dto.getEmail());
	}

	@Test
	void testGetPassword() {
		assertEquals("root", dto.getPassword());
	}

	@Test
	void testGetMobile() {
		assertEquals("9876543210", dto.getMobile());
	}

	@Test
	void testGetAddress() {
		assertEquals("Madurai", dto.getAddress());
	}
	//negative tests
	@Test
	void testGetUserIdNegative() {
		assertNotEquals(103, dto.getUserId());
	}

}
