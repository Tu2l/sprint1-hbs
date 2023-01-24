package com.hbs.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

	User user;
	@BeforeEach
	void setUp() {
		user = new User();
		user.setUserId(1);
		user.setUserName("Jack");
		//user.setRole(null);
		user.setAddress("India");
		user.setEmail("s@gmail.com");
		user.setMobile("1234567890");
		user.setPassword("qwerty");
		
	}
	@Test
	void testGetUserId() {
		assertEquals(1, user.getUserId());
	}

	@Test
	void testGetUserName() {
		assertEquals("Jack",user.getUserName());
	}

	@Test
	void testGetEmail() {
		assertEquals("s@gmail.com",user.getEmail());	}

	@Test
	void testGetPassword() {
		assertEquals("qwerty",user.getPassword());
	}

//	@Test
//	void testGetRole() {
//		fail("Not yet implemented");
//	}

	@Test
	void testGetMobile() {
		assertEquals("1234567890",user.getMobile());
	}

	@Test
	void testGetAddress() {
		assertEquals("India",user.getAddress());
	}

}
