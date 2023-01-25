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

import com.hbs.auth.JwtRequest;
import com.hbs.dto.UserDTO;
import com.hbs.entities.UserRole;
import com.hbs.exceptions.ActiveBookingFoundException;
import com.hbs.exceptions.InvalidEmailFormatException;
import com.hbs.exceptions.InvalidMobileNumberFormatException;
import com.hbs.exceptions.UserAlreadyExistsException;
import com.hbs.exceptions.UserNotFoundException;
import com.hbs.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<UserDTO> add(@Valid @RequestBody UserDTO dto)
			throws UserAlreadyExistsException, InvalidEmailFormatException, InvalidMobileNumberFormatException {
		return new ResponseEntity<>(userService.add(dto), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO dto, @PathVariable int id)
			throws UserNotFoundException, UserAlreadyExistsException, InvalidEmailFormatException,
			InvalidMobileNumberFormatException {
		
		dto.setUserId(id);
		
		return new ResponseEntity<>(userService.update(dto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UserDTO> remove(@PathVariable int id) throws UserNotFoundException, ActiveBookingFoundException {
		return new ResponseEntity<>(userService.remove(id), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() throws UserNotFoundException {
		return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> find(@PathVariable int id) throws UserNotFoundException {
		return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
	}
	
	@GetMapping("/{email}/{role}")
	public ResponseEntity<UserDTO> find(@PathVariable String email, @PathVariable String role) throws UserNotFoundException {
		return new ResponseEntity<>(userService.findByEmailAndRole(email,UserRole.set(role)), HttpStatus.OK);
	}
	
	@PostMapping("/signout")
	public ResponseEntity<UserDTO> signOut(@RequestBody JwtRequest jwtRequest) throws UserNotFoundException {
		return new ResponseEntity<>(userService.signOut(jwtRequest), HttpStatus.OK);
	}

}
