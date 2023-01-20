package com.hbs.service;

import java.util.List;
import com.hbs.entities.User;
import com.hbs.exceptions.UserAlreadyExistsException;
import com.hbs.exceptions.UserNotFoundException;

public interface UserService {
	public User addUser(User user) throws UserAlreadyExistsException;

	public User updateUser(User user) throws UserNotFoundException, UserAlreadyExistsException;

	public User removeUser(int id) throws UserNotFoundException;

	public List<User> showAllUser() throws UserNotFoundException;

	public User findById(int user) throws UserNotFoundException;
}