package com.hbs.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hbs.dto.UserDTO;
import com.hbs.entities.UserRole;
import com.hbs.exceptions.InvalidEmailFormatException;
import com.hbs.exceptions.InvalidMobileNumberFormatException;
import com.hbs.exceptions.UserAlreadyExistsException;
import com.hbs.exceptions.UserNotFoundException;
import com.hbs.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	@InjectMocks
	private UserController controller;

	@Mock
	private UserService userService;

	@Test
	void testAdd() throws UserAlreadyExistsException, InvalidEmailFormatException, InvalidMobileNumberFormatException {
		UserDTO dto = new UserDTO();
		when(userService.add(dto)).thenReturn(dto);
		ResponseEntity<UserDTO> response = controller.add(dto);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	void testUpdate() throws UserNotFoundException, UserAlreadyExistsException, InvalidEmailFormatException,
			InvalidMobileNumberFormatException {
		UserDTO dto = new UserDTO();
		when(userService.update(dto)).thenReturn(dto);
		ResponseEntity<UserDTO> response = controller.update(dto, 1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testFindAll() throws UserNotFoundException {
		List<UserDTO> users = new ArrayList<>();
		when(userService.findAll()).thenReturn(users);
		ResponseEntity<List<UserDTO>> response = controller.findAll();
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testFindById() throws UserNotFoundException {
		UserDTO dto = new UserDTO();
		when(userService.findById(1)).thenReturn(dto);
		ResponseEntity<UserDTO> response = controller.find(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testFindByEmailAndRole() throws UserNotFoundException {
		UserDTO dto = new UserDTO();
		when(userService.findByEmailAndRole("user1@ymail.com", UserRole.USER)).thenReturn(dto);
		ResponseEntity<UserDTO> response = controller.find("user1@ymail.com", "USER");
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
