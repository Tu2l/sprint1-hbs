package com.hbs.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hbs.auth.JwtRequest;
import com.hbs.dto.UserDTO;
import com.hbs.exceptions.AdminAlreadyExistsException;
import com.hbs.exceptions.AdminNotFoundException;
import com.hbs.service.AdminService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

	@Mock
	private AdminService adminService;

	private UserDTO userDTO;

	@InjectMocks
	private AdminController adminController;

	@BeforeEach
	void setup() {
		userDTO = new UserDTO();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAdd() throws AdminAlreadyExistsException {
		when(adminService.add(userDTO)).thenReturn(userDTO);
		ResponseEntity<UserDTO> response = adminController.add(userDTO);
		verify(adminService).add(userDTO);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(userDTO, response.getBody());
	}

	@Test
	void testSignOut() throws AdminNotFoundException {
		JwtRequest jwtRequest = new JwtRequest();
		when(adminService.signOut(jwtRequest)).thenReturn(userDTO);
		ResponseEntity<UserDTO> response = adminController.signOut(jwtRequest);
		verify(adminService).signOut(jwtRequest);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(userDTO, response.getBody());
	}
}
