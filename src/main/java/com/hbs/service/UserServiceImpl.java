package com.hbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hbs.auth.JwtRequest;
import com.hbs.auth.JwtResponse;
import com.hbs.dto.UserDTO;
import com.hbs.entities.User;
import com.hbs.entities.UserRole;
import com.hbs.exceptions.AdminNotFoundException;
import com.hbs.exceptions.InvalidCredentialsException;
import com.hbs.exceptions.InvalidEmailFormatException;
import com.hbs.exceptions.InvalidMobileNumberFormatException;
import com.hbs.exceptions.UserAlreadyExistsException;
import com.hbs.exceptions.UserNotFoundException;
import com.hbs.repository.UserRepository;
import com.hbs.util.MapperUtil;
import com.hbs.util.ValidationUtil;

@Service
public class UserServiceImpl implements UserService {
	private static final String USER_NOT_FOUND_EXCEPTION = "UserDTO not found with id: ";
	private static final String USER_NOT_FOUND_EMAIL_EXCEPTION = "UserDTO not found with email: ";
	private static final String USER_ALREADY_EXISTS = "UserDTO already exists with email: ";
	private static final String INVALID_EMAIL_FORMAT = "Invalid email: ";
	private static final String INVALID_MOBILE_NUMBER_FORMAT = "Invalid mobile number";

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtService jwtService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private void validateUser(User user) throws InvalidEmailFormatException, InvalidMobileNumberFormatException {
		// validate email, phone
		if (!ValidationUtil.validateEmail(user.getEmail()))
			throw new InvalidEmailFormatException(INVALID_EMAIL_FORMAT + user.getEmail());
		if (!ValidationUtil.validatePhoneNumber(user.getMobile()))
			throw new InvalidMobileNumberFormatException(INVALID_MOBILE_NUMBER_FORMAT + user.getMobile());

	}

	@Override
	public UserDTO add(UserDTO dto)
			throws UserAlreadyExistsException, InvalidEmailFormatException, InvalidMobileNumberFormatException {

		User user = MapperUtil.mapToUser(dto);

		if (userRepository.findByEmail(user.getEmail()).isPresent())
			throw new UserAlreadyExistsException(USER_ALREADY_EXISTS + user.getEmail());

		validateUser(user);

		user.setRole(UserRole.USER);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return MapperUtil.mapToUserDto(userRepository.save(user));
	}

	@Override
	public UserDTO update(UserDTO dto) throws UserNotFoundException, UserAlreadyExistsException,
			InvalidEmailFormatException, InvalidMobileNumberFormatException {

		User user = MapperUtil.mapToUser(dto);

		UserDTO find = findById(user.getUserId());
		if (!(user.getEmail().equalsIgnoreCase(find.getEmail()))
				&& userRepository.findByEmail(user.getEmail()).isPresent())
			throw new UserAlreadyExistsException(USER_ALREADY_EXISTS + user.getEmail());

		validateUser(user);

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return MapperUtil.mapToUserDto(userRepository.save(user));
	}

	@Override
	public UserDTO remove(int id) throws UserNotFoundException {
		UserDTO find = findById(id);
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