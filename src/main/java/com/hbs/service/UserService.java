package com.hbs.service;

import java.util.List;

import com.hbs.auth.JwtRequest;
import com.hbs.auth.JwtResponse;
import com.hbs.dto.UserDTO;
import com.hbs.entities.UserRole;
import com.hbs.exceptions.ActiveBookingFoundException;
import com.hbs.exceptions.InvalidCredentialsException;
import com.hbs.exceptions.InvalidEmailFormatException;
import com.hbs.exceptions.InvalidMobileNumberFormatException;
import com.hbs.exceptions.UserAlreadyExistsException;
import com.hbs.exceptions.UserNotFoundException;

public interface UserService {
	UserDTO add(UserDTO userDto)throws UserAlreadyExistsException, InvalidEmailFormatException, InvalidMobileNumberFormatException;

	UserDTO update(UserDTO userDto) throws UserNotFoundException, UserAlreadyExistsException, InvalidEmailFormatException,
			InvalidMobileNumberFormatException;

	UserDTO remove(int id) throws UserNotFoundException, ActiveBookingFoundException;

	List<UserDTO> findAll() throws UserNotFoundException;

	UserDTO findById(int id) throws UserNotFoundException;

	UserDTO findByEmail(String email) throws UserNotFoundException;
	
	UserDTO findByEmailAndRole(String email,UserRole role) throws UserNotFoundException;

	JwtResponse signIn(JwtRequest request) throws UserNotFoundException, InvalidCredentialsException;

	UserDTO signOut(JwtRequest request) throws UserNotFoundException;

}