package com.hbs.service;

import com.hbs.auth.JwtRequest;
import com.hbs.auth.JwtResponse;
import com.hbs.entities.User;
import com.hbs.exceptions.AdminAlreadyExistsException;
import com.hbs.exceptions.AdminNotFoundException;
import com.hbs.exceptions.InvalidCredentialsException;

public interface AdminService {
	JwtResponse signIn(JwtRequest request) throws AdminNotFoundException, InvalidCredentialsException;

	User signOut(JwtRequest request) throws AdminNotFoundException;
	
	User add(User admin) throws AdminAlreadyExistsException;
	
	User findByEmail(String email) throws AdminNotFoundException;
}
