package com.hbs.service;

import com.hbs.auth.JwtRequest;
import com.hbs.auth.JwtResponse;
import com.hbs.dto.UserDTO;
import com.hbs.exceptions.AdminAlreadyExistsException;
import com.hbs.exceptions.AdminNotFoundException;
import com.hbs.exceptions.InvalidCredentialsException;

public interface AdminService {
	JwtResponse signIn(JwtRequest request) throws AdminNotFoundException, InvalidCredentialsException;

	UserDTO signOut(JwtRequest request) throws AdminNotFoundException;
	
	UserDTO add(UserDTO dto) throws AdminAlreadyExistsException;
	
	UserDTO findByEmail(String email) throws AdminNotFoundException;
}
