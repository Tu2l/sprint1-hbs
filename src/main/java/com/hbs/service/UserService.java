package com.hbs.service;

import java.util.List;
import com.hbs.entities.User;
import com.hbs.exceptions.UserAlreadyExistsException;
import com.hbs.exceptions.UserNotFoundException;

public interface UserService {
	public User add(User user) throws UserAlreadyExistsException;

	public User update(User user) throws UserNotFoundException, UserAlreadyExistsException;

	public User remove(int id) throws UserNotFoundException;

	public List<User> findAll() throws UserNotFoundException;

	public User findById(int user) throws UserNotFoundException;
}