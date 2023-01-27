package com.hbs.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hbs.auth.JwtRequest;
import com.hbs.auth.JwtResponse;
import com.hbs.dto.UserDTO;
import com.hbs.entities.BookingDetails;
import com.hbs.entities.User;
import com.hbs.entities.UserRole;
import com.hbs.exceptions.ActiveBookingFoundException;
import com.hbs.exceptions.AdminNotFoundException;
import com.hbs.exceptions.InvalidCredentialsException;
import com.hbs.exceptions.InvalidEmailFormatException;
import com.hbs.exceptions.InvalidMobileNumberFormatException;
import com.hbs.exceptions.UserAlreadyExistsException;
import com.hbs.exceptions.UserNotFoundException;
import com.hbs.repository.BookingDetailsRepository;
import com.hbs.repository.UserRepository;
import com.hbs.service.JwtService;
import com.hbs.service.UserService;
import com.hbs.util.MapperUtil;
import com.hbs.util.ValidationUtil;

@Service
public class UserServiceImpl implements UserService {
	private static final String USER_NOT_FOUND_EXCEPTION = "User not found with id: ";
	private static final String USER_NOT_FOUND_EMAIL_EXCEPTION = "User not found with email: ";
	private static final String USER_EMAIL_ALREADY_EXISTS = "User already exists with email: ";
	private static final String USER_MOBILE_ALREADY_EXISTS = "User already exists with mobile: ";
	private static final String INVALID_EMAIL_FORMAT = "Invalid email: ";
	private static final String INVALID_MOBILE_NUMBER_FORMAT = "Invalid mobile number";
	private static final String ACTIVE_BOOKING_FOUND_MESSAGE = "Active booking found";

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BookingDetailsRepository bookingRepository;
	@Autowired
	private JwtService jwtService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	void validateUser(UserDTO dto) throws InvalidEmailFormatException, InvalidMobileNumberFormatException {
		// validate email, phone
		if (!ValidationUtil.validateEmail(dto.getEmail()))
			throw new InvalidEmailFormatException(INVALID_EMAIL_FORMAT + dto.getEmail());
		if (!ValidationUtil.validatePhoneNumber(dto.getMobile()))
			throw new InvalidMobileNumberFormatException(INVALID_MOBILE_NUMBER_FORMAT + dto.getMobile());

	}

	@Override
	public UserDTO add(UserDTO dto)
			throws UserAlreadyExistsException, InvalidEmailFormatException, InvalidMobileNumberFormatException {

		validateUser(dto);

		if (userRepository.findByEmail(dto.getEmail()).isPresent())
			throw new UserAlreadyExistsException(USER_EMAIL_ALREADY_EXISTS + dto.getEmail());
		
		if (userRepository.findByMobile(dto.getMobile()).isPresent())
			throw new UserAlreadyExistsException(USER_MOBILE_ALREADY_EXISTS + dto.getMobile());

		User user = MapperUtil.mapToUser(dto);

		user.setRole(UserRole.USER);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return MapperUtil.mapToUserDto(userRepository.save(user));
	}

	@Override
	public UserDTO update(UserDTO dto) throws UserNotFoundException, UserAlreadyExistsException,
			InvalidEmailFormatException, InvalidMobileNumberFormatException {
		
		validateUser(dto);

		UserDTO find = findById(dto.getUserId());
		if (!(dto.getEmail().equalsIgnoreCase(find.getEmail()))
				&& userRepository.findByEmail(dto.getEmail()).isPresent())
			throw new UserAlreadyExistsException(USER_EMAIL_ALREADY_EXISTS + dto.getEmail());

		if (!(dto.getMobile().equals(find.getMobile()))
				&&userRepository.findByMobile(dto.getMobile()).isPresent())
			throw new UserAlreadyExistsException(USER_MOBILE_ALREADY_EXISTS + dto.getMobile());
		

		User user = MapperUtil.mapToUser(dto);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return MapperUtil.mapToUserDto(userRepository.save(user));
	}

	@Override
	public UserDTO remove(int id) throws UserNotFoundException, ActiveBookingFoundException {
		UserDTO find = findById(id);

		if (bookingRepository.findByDateAndUserIdCount(LocalDate.now(), id) > 0)
			throw new ActiveBookingFoundException(ACTIVE_BOOKING_FOUND_MESSAGE);

		List<BookingDetails> bookings = bookingRepository.findByUserId(id);
		if (!bookings.isEmpty())
			bookingRepository.deleteAll(bookings);

		userRepository.deleteById(find.getUserId());

		return find;
	}

	@Override
	public List<UserDTO> findAll() throws UserNotFoundException {
		return MapperUtil.mapToUserDtoList(userRepository.findAllByRole(UserRole.USER));
	}

	@Override
	public UserDTO findById(int userId) throws UserNotFoundException {
		User user = userRepository.findByUserIdAndRole(userId, UserRole.USER)
				.orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_EXCEPTION + userId));
		return MapperUtil.mapToUserDto(user);

	}

	@Override
	public UserDTO findByEmail(String email) throws UserNotFoundException {
		return findByEmailAndRole(email, UserRole.USER);
	}

	@Override
	public JwtResponse signIn(JwtRequest request) throws UserNotFoundException, InvalidCredentialsException {
		try {
			return jwtService.authenticate(request, UserRole.USER);
		} catch (AdminNotFoundException e) {
			throw new UserNotFoundException(e.getMessage());
		}
	}

	@Override
	public UserDTO signOut(JwtRequest request) throws UserNotFoundException {
		UserDTO find = findByEmail(request.getEmail());
		jwtService.invalidateToken(request.getEmail());
		return find;
	}

	@Override
	public UserDTO findByEmailAndRole(String email, UserRole role) throws UserNotFoundException {
		User user = userRepository.findByEmailAndRole(email, role)
				.orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_EMAIL_EXCEPTION + email));

		return MapperUtil.mapToUserDto(user);
	}
}