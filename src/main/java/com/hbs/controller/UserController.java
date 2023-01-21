package com.hbs.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.hbs.dto.UserDTO;
import com.hbs.entities.User;
import com.hbs.exceptions.UserAlreadyExistsException;
import com.hbs.exceptions.UserNotFoundException;
import com.hbs.service.UserService;
import com.hbs.util.MapperUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<UserDTO> add(@Valid @RequestBody UserDTO userDto) throws UserAlreadyExistsException {
		User user = userService.add(MapperUtil.mapToUser(userDto));
		return new ResponseEntity<>(MapperUtil.mapToUserDto(user), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO userDto) throws UserNotFoundException, UserAlreadyExistsException {
		User user = userService.update(MapperUtil.mapToUser(userDto));
		return new ResponseEntity<>(MapperUtil.mapToUserDto(user), HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<UserDTO> remove(@PathVariable int userId) throws UserNotFoundException {
		return new ResponseEntity<>(MapperUtil.mapToUserDto(userService.remove(userId)), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<UserDTO>> findAll() throws UserNotFoundException {
		List<User> userList = userService.findAll();
		return new ResponseEntity<>(MapperUtil.mapToUserDtoList(userList), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> find(@PathVariable int id) throws UserNotFoundException {
		return new ResponseEntity<>(MapperUtil.mapToUserDto(userService.findById(id)), HttpStatus.OK);
	}

}
