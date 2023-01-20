package com.hbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hbs.entities.User;
import com.hbs.exceptions.UserAlreadyExistsException;
import com.hbs.exceptions.UserNotFoundException;
import com.hbs.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private static final String USER_NOT_FOUND_EXCEPTION = "User not found with id: ";
	private static final String USER_ALREADY_EXISTS = "User already exists with email: ";

	@Autowired
	private UserRepository userRepository;

	@Override
	public User addUser(User user) throws UserAlreadyExistsException {
		User find = userRepository.findByEmail(user.getEmail());
		if (find != null)
			throw new UserAlreadyExistsException(USER_ALREADY_EXISTS + user.getEmail());

		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) throws UserNotFoundException, UserAlreadyExistsException {
		User find = findById(user.getUserId());
		if (!(user.getEmail().equalsIgnoreCase(find.getEmail())) && userRepository.findByEmail(user.getEmail()) != null)
			throw new UserAlreadyExistsException(USER_ALREADY_EXISTS + user.getEmail());

		return userRepository.save(find);
	}

	@Override
	public User removeUser(int id) throws UserNotFoundException {
		User find = findById(id);
		userRepository.deleteById(find.getUserId());
		return find;
	}

	@Override
	public List<User> showAllUser() throws UserNotFoundException {
		return userRepository.findAll();
	}

	@Override
	public User findById(int userId) throws UserNotFoundException {
		return userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_EXCEPTION + userId));

	}
}