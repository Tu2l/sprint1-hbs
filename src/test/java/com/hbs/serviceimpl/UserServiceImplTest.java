package com.hbs.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.hbs.dto.UserDTO;
import com.hbs.entities.User;
import com.hbs.entities.UserRole;
import com.hbs.repository.BookingDetailsRepository;
import com.hbs.repository.UserRepository;
import com.hbs.util.MapperUtil;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;
	@Mock
	private BookingDetailsRepository bookingRepository;

	@Mock
	private BCryptPasswordEncoder passwordEncoder;

	@InjectMocks
	private UserServiceImpl userService;

	private UserDTO dto, dto2;
	private User user;
	private List<UserDTO> users = new ArrayList<>();
	private MockedStatic<MapperUtil> mockedUtil;

	@BeforeAll
	static void init() {
		// mockStatic(MapperUtil.class);
	}

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

		dto2 = new UserDTO();
		dto2.setUserId(1);
		dto2.setUsername("user1");
		dto2.setEmail("real1@user.com");
		dto2.setPassword("paasword");
		dto2.setMobile("1234567890");
		dto2.setAddress("japan");

	}

	@AfterEach
	void close() {
		mockedUtil.close();
	}

	@Test
	void testAddUser() {
		try {
			mockedUtil = mockStatic(MapperUtil.class);
			when(MapperUtil.mapToUserDto(user)).thenReturn(dto);
			when(MapperUtil.mapToUser(dto)).thenReturn(user);
			when(userService.add(dto)).thenReturn(dto);
			assertEquals(dto.toString(), userService.add(dto).toString());
		} catch (Exception e) {
			fail("Exception not expected");
		}
	}

	/*
	 * @Test void testAddUserFail() {
	 * assertThrows(UserAlreadyExistsException.class,()->{ mockedUtil =
	 * mockStatic(MapperUtil.class); //
	 * when(MapperUtil.mapToUser(dto)).thenReturn(user); //
	 * doThrow(UserAlreadyExistsException.class).when(userService).validateUser(dto)
	 * ; doAnswer((i)->{ userService.validateUser(dto); return null;
	 * }).doThrow(UserAlreadyExistsException.class); //
	 * when(MapperUtil.mapToUserDto(user)).thenReturn(dto);
	 * when(userService.add(dto)).thenThrow(UserAlreadyExistsException.class);
	 * userService.add(dto);
	 * 
	 * });
	 * 
	 * }
	 */

	@Test
	void testUpdate() {
		try {
			mockedUtil = mockStatic(MapperUtil.class);
			when(MapperUtil.mapToUserDto(user)).thenReturn(dto);
			when(MapperUtil.mapToUser(dto)).thenReturn(user);
			when(userRepository.findByUserIdAndRole(dto.getUserId(), UserRole.USER)).thenReturn(Optional.of(user));
			when(userService.findById(dto.getUserId())).thenReturn(dto);
			when(userService.update(dto)).thenReturn(dto);

			dto.setUsername("user2");
			assertEquals(dto, userService.update(dto));
		} catch (Exception e) {
//			e.printStackTrace();
			fail("Exception not expected");
		}
	}

	@Test
	void testRemove() {
		try {
			mockedUtil = mockStatic(MapperUtil.class);
			when(MapperUtil.mapToUserDto(user)).thenReturn(dto);
			when(userRepository.findByUserIdAndRole(dto.getUserId(), UserRole.USER)).thenReturn(Optional.of(user));

			Mockito.lenient().when(userRepository.findById(dto.getUserId())).thenReturn(Optional.of(user))
					.then(i -> userService.remove(dto.getUserId()));

			assertEquals(dto.toString(), userService.remove(dto.getUserId()).toString());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testFindAll() {
		try {
			mockedUtil = mockStatic(MapperUtil.class);
			List<User> usersList = new ArrayList<>();
			usersList.add(user);

			when(MapperUtil.mapToUserDto(user)).thenReturn(dto);
			when(MapperUtil.mapToUserDtoList(usersList)).thenReturn(users);

			when(userRepository.findAllByRole(UserRole.USER)).thenReturn(usersList).then((i) -> {
				return userService.findAll();
			});

			assertEquals(users, userService.findAll());
		} catch (Exception e) {
			fail("Exception not expected");
		}
	}

	@Test
	void testFindById() {
		try {
			mockedUtil = mockStatic(MapperUtil.class);
			when(MapperUtil.mapToUserDto(user)).thenReturn(dto);
			when(MapperUtil.mapToUser(dto)).thenReturn(user);
			when(userRepository.findByUserIdAndRole(dto.getUserId(), UserRole.USER)).thenReturn(Optional.of(user))
					.then((i) -> {
						return userService.findById(dto.getUserId());
					});

			assertEquals(dto.toString(), userService.findById(dto.getUserId()).toString());
		} catch (Exception e) {
			fail("Exception not expected");
		}
	}

	@Test
	void testFindByEmail() {
		try {
			mockedUtil = mockStatic(MapperUtil.class);
			when(MapperUtil.mapToUserDto(user)).thenReturn(dto);
			when(MapperUtil.mapToUser(dto)).thenReturn(user);

			when(userRepository.findByEmailAndRole(dto.getEmail(), UserRole.USER)).thenReturn(Optional.of(user))
					.then((i) -> {
						return userService.findByEmail(dto.getEmail());
					});

			assertEquals(dto.toString(), userService.findByEmail(dto.getEmail()).toString());
		} catch (Exception e) {
			fail("Exception not expected");
		}
	}

	@Test
	void testFindByEmailAndRole() {
		try {
			mockedUtil = mockStatic(MapperUtil.class);
			when(MapperUtil.mapToUserDto(user)).thenReturn(dto);
			when(MapperUtil.mapToUser(dto)).thenReturn(user);

			when(userRepository.findByEmailAndRole(dto.getEmail(), UserRole.USER)).thenReturn(Optional.of(user))
					.then((i) -> {
						return userService.findByEmailAndRole(dto.getEmail(), UserRole.USER);
					});

			assertEquals(dto.toString(), userService.findByEmailAndRole(dto.getEmail(), UserRole.USER).toString());
		} catch (Exception e) {
			fail("Exception not expected");
		}
	}

}
