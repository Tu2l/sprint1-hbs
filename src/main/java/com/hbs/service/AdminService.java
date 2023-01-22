package com.hbs.service;

import com.hbs.entities.Admin;
import com.hbs.exceptions.AdminAlreadyExistsException;
import com.hbs.exceptions.AdminNotFoundException;
import com.hbs.exceptions.InvalidCredentialsException;

public interface AdminService {
	Admin signIn(Admin admin) throws AdminNotFoundException, InvalidCredentialsException;

	Admin signOut(Admin admin) throws AdminNotFoundException;
	
	Admin add(Admin admin) throws AdminAlreadyExistsException;
	
	Admin findByEmail(String email) throws AdminNotFoundException;
}
