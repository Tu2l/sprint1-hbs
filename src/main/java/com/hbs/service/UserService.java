package com.hbs.service;

import java.util.List;

import com.hbs.auth.JwtRequest;
import com.hbs.auth.JwtResponse;
import com.hbs.entities.User;
import com.hbs.entities.UserRole;
import com.hbs.exceptions.InvalidCredentialsException;
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
	
	User findByEmailAndRole(String email,UserRole role) throws UserNotFoundException;

	JwtResponse signIn(JwtRequest request) throws UserNotFoundException, InvalidCredentialsException;

	User signOut(JwtRequest request) throws UserNotFoundException;

}