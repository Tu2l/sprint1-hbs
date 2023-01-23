package com.hbs.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hbs.entities.User;
import com.hbs.exceptions.UserAlreadyExistsException;
import com.hbs.exceptions.UserNotFoundException;
import com.hbs.repository.UserRepository;

class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	private User user;

	@BeforeEach
	void setUp() throws Exception {
		user = new User(1, "virat kohli", "vk18@ymail.com", "password", "user", "1234567890", "1234 dwaraka");
		userService = new UserServiceImpl();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAddUser() throws UserAlreadyExistsException {

		when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
		when(userRepository.save(user)).thenReturn(user);
		User result = userService.add(user);
		assertEquals(user, result);
		verify(userRepository, times(1)).findByEmail(user.getEmail());
		verify(userRepository, times(1)).save(user);
	}

	@Test
	void testAddDuplicateEmail_throwsUserAlreadyExistsException() {
		when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
		assertThrows(UserAlreadyExistsException.class, () -> {
			userService.add(user);
		});
		verify(userRepository, times(1)).findByEmail(user.getEmail());
		verify(userRepository, times(0)).save(user);
	}

	@Test
	void testUpdateDuplicateEmail_throwsUserAlreadyExistsException() throws UserNotFoundException {
		
	    User existingUser = new User(1, "virat kohli", "viratkohli@ymail.com", "password", "user", "1234567890", "1234 dwaraka");
	    when(userRepository.findById(user.getUserId())).thenReturn(java.util.Optional.of(existingUser));
	    when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
	    assertThrows(UserAlreadyExistsException.class, () -> {
	        userService.update(user);
	    });
	    verify(userRepository, times(1)).findById(user.getUserId());
	    verify(userRepository, times(1)).findByEmail(user.getEmail());
	    verify(userRepository, times(0)).save(user);
	}
	
	@Test
	void testUpdateUserNotFound_throwsUserNotFoundException() {
		user = new User(1, "virat kohli", "vk18@ymail.com", "password", "user", "1234567890", "1234 dwaraka");
	    when(userRepository.findById(user.getUserId())).thenReturn(Optional.empty());
	    assertThrows(UserNotFoundException.class, () -> {
	        userService.update(user);
	    });
	    verify(userRepository, times(1)).findById(user.getUserId());
	    verify(userRepository, times(0)).findByEmail(user.getEmail());
	    verify(userRepository, times(0)).save(user);
	}
	
	@Test
	void testRemoveValidId_returnsUser() throws UserNotFoundException {
		user = new User(1, "virat kohli", "vk18@ymail.com", "password", "user", "1234567890", "1234 dwaraka");
		when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
	    assertEquals(user, userService.remove(user.getUserId()));
	    verify(userRepository, times(1)).findById(user.getUserId());
	    verify(userRepository, times(1)).deleteById(user.getUserId());
	}
}
