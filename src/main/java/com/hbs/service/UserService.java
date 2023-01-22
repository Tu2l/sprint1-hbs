package com.hbs.service;

import java.util.List;
import com.hbs.entities.User;
import com.hbs.exceptions.InvalidEmailFormatException;
import com.hbs.exceptions.InvalidMobileNumberFormatException;
import com.hbs.exceptions.UserAlreadyExistsException;
import com.hbs.exceptions.UserNotFoundException;

public interface UserService {
	User add(User user)
			throws UserAlreadyExistsException, InvalidEmailFormatException, InvalidMobileNumberFormatException;

	User update(User user) throws UserNotFoundException, UserAlreadyExistsException, InvalidEmailFormatException,
			InvalidMobileNumberFormatException;

	User remove(int id) throws UserNotFoundException;

	List<User> findAll() throws UserNotFoundException;

	User findById(int user) throws UserNotFoundException;

	User findByEmail(String email) throws UserNotFoundException;
}