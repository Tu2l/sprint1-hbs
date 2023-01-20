package com.hbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbs.entities.User;
import com.hbs.exceptions.UserAlreadyExistsException;
import com.hbs.exceptions.UserNotFoundException;
import com.hbs.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<User> add(@RequestBody User user) throws UserAlreadyExistsException {
		User addedUser = userService.addUser(user);
		return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<User> update(@RequestBody User user) throws UserNotFoundException, UserAlreadyExistsException {
		User updatedUser = userService.updateUser(user);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<User> remove(@PathVariable int userId) throws UserNotFoundException {
		return new ResponseEntity<>(userService.removeUser(userId), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<User>> findAll() throws UserNotFoundException {
		List<User> userList = userService.showAllUser();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> find(@PathVariable int id) throws UserNotFoundException {
		User showUser;
		showUser = userService.findById(id);
		return new ResponseEntity<>(showUser, HttpStatus.OK);
	}

}
