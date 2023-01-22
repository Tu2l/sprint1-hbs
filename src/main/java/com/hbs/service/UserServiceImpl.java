package com.hbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.hbs.entities.User;
import com.hbs.exceptions.InvalidEmailFormatException;
import com.hbs.exceptions.InvalidMobileNumberFormatException;
import com.hbs.exceptions.UserAlreadyExistsException;
import com.hbs.exceptions.UserNotFoundException;
import com.hbs.repository.UserRepository;
import com.hbs.util.ValidationUtil;

@Service
public class UserServiceImpl implements UserService {
	private static final String USER_NOT_FOUND_EXCEPTION = "User not found with id: ";
	private static final String USER_NOT_FOUND_EMAIL_EXCEPTION = "User not found with email: ";
	private static final String USER_ALREADY_EXISTS = "User already exists with email: ";
	private static final String INVALID_EMAIL_FORMAT = "Invalid email: ";
	private static final String INVALID_MOBILE_NUMBER_FORMAT = "Invalid mobile number";

	@Autowired
	private UserRepository userRepository;

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
	public User add(User user)
			throws UserAlreadyExistsException, InvalidEmailFormatException, InvalidMobileNumberFormatException {
//		User find = userRepository.findByEmail(user.getEmail());
		if (userRepository.findByEmail(user.getEmail()).isPresent())
			throw new UserAlreadyExistsException(USER_ALREADY_EXISTS + user.getEmail());

		validateUser(user);

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return userRepository.save(user);
	}

	@Override
	public User update(User user) throws UserNotFoundException, UserAlreadyExistsException, InvalidEmailFormatException,
			InvalidMobileNumberFormatException {
		User find = findById(user.getUserId());
		if (!(user.getEmail().equalsIgnoreCase(find.getEmail())) && userRepository.findByEmail(user.getEmail()) != null)
			throw new UserAlreadyExistsException(USER_ALREADY_EXISTS + user.getEmail());

		validateUser(user);

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return userRepository.save(user);
	}

	@Override
	public User remove(int id) throws UserNotFoundException {
		User find = findById(id);
		userRepository.deleteById(find.getUserId());
		return find;
	}

	@Override
	public List<User> findAll() throws UserNotFoundException {
		return userRepository.findAll();
	}

	@Override
	public User findById(int userId) throws UserNotFoundException {
		return userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_EXCEPTION + userId));

	}

	@Override
	public User findByEmail(String email) throws UserNotFoundException {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_EMAIL_EXCEPTION + email));
	}
}