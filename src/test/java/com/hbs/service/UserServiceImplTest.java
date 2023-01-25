package com.hbs.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.hbs.dto.UserDTO;
import com.hbs.entities.User;
import com.hbs.exceptions.InvalidEmailFormatException;
import com.hbs.exceptions.InvalidMobileNumberFormatException;
import com.hbs.exceptions.UserAlreadyExistsException;
import com.hbs.repository.UserRepository;
import com.hbs.serviceimpl.UserServiceImpl;
import com.hbs.util.MapperUtil;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private BCryptPasswordEncoder passwordEncoder;

	@InjectMocks
	private UserServiceImpl userService;

	private UserDTO dto;
	private User user;
	private List<UserDTO> users = new ArrayList<>();
	

	@BeforeEach
	void setUp() throws Exception {
		dto = new UserDTO();
		dto.setUserId(1);
		dto.setUsername("user_one");
		dto.setEmail("real1@user.com");
		dto.setPassword("paasword");
		dto.setMobile("1234567890");
		dto.setAddress("japan");

		users.add(dto);
		
		user = MapperUtil.mapToUser(dto);
	}

	@Test
	void testAddUser() {
		try{
			mockStatic(MapperUtil.class);
			when(MapperUtil.mapToUserDto(user)).thenReturn(dto);
			when(MapperUtil.mapToUser(dto)).thenReturn(user);
			when(userService.add(dto)).thenReturn(dto);
			assertEquals(dto.toString(), userService.add(dto).toString());
		} catch (UserAlreadyExistsException | InvalidEmailFormatException | InvalidMobileNumberFormatException e) {
			fail("Exception not expected");
		}
	}


}
