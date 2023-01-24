package com.hbs.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hbs.dto.UserDTO;
import com.hbs.entities.User;
import com.hbs.exceptions.InvalidEmailFormatException;
import com.hbs.exceptions.InvalidMobileNumberFormatException;
import com.hbs.exceptions.UserAlreadyExistsException;
import com.hbs.exceptions.UserNotFoundException;
import com.hbs.repository.UserRepository;
import com.hbs.serviceimpl.UserServiceImpl;

class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@MockBean
	private PasswordEncoder mockPasswordEncoder;

	@InjectMocks
	private UserService userService;

	private UserDTO userDTO;
	private User user;

	@BeforeEach
	void setUp() throws Exception {

		userDTO = new UserDTO();
		userDTO.setUsername("user_one");
		userDTO.setEmail("real1@user.com");
		userDTO.setPassword("qwerty@1237");
		userDTO.setMobile("1234567890");
		userDTO.setAddress("japan");

		userService = new UserServiceImpl();
		mockPasswordEncoder = mock(PasswordEncoder.class);
		when(mockPasswordEncoder.encode("password")).thenReturn("encoded_password");

		Field passwordEncoderField = userService.getClass().getDeclaredField("passwordEncoder");
		passwordEncoderField.setAccessible(true);
		passwordEncoderField.set(userService, mockPasswordEncoder);
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testAddUser()
			throws UserAlreadyExistsException, InvalidEmailFormatException, InvalidMobileNumberFormatException {
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername("username");
		userDTO.setEmail("email@example.com");
		userDTO.setPassword("password");
		userDTO.setMobile("1234567890");
		userDTO.setAddress("address");

		UserDTO addedUser = userService.add(userDTO);
		verify(mockPasswordEncoder, times(1)).encode("password");
		assertNotNull(addedUser);
		assertEquals("username", addedUser.getUsername());
		assertEquals("email@example.com", addedUser.getEmail());
		assertEquals("encoded_password", addedUser.getPassword());
		assertEquals("1234567890", addedUser.getMobile());
		assertEquals("address", addedUser.getAddress());
	}

	@Test
	void testUpdateUser() throws UserNotFoundException, UserAlreadyExistsException, InvalidEmailFormatException,
			InvalidMobileNumberFormatException {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(1);
		userDTO.setUsername("username");
		userDTO.setEmail("email@example.com");
		userDTO.setPassword("password");
		userDTO.setMobile("1234567890");
		userDTO.setAddress("address");
		UserDTO updatedUser = userService.update(userDTO);
		verify(mockPasswordEncoder, times(1)).encode("password");
		assertNotNull(updatedUser);
		assertEquals(1, updatedUser.getUserId());
		assertEquals("username", updatedUser.getUsername());
		assertEquals("email@example.com", updatedUser.getEmail());
		assertEquals("encoded_password", updatedUser.getPassword());
		assertEquals("1234567890", updatedUser.getMobile());
		assertEquals("address", updatedUser.getAddress());
	}

}
