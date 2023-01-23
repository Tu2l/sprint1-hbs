package com.hbs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbs.auth.JwtRequest;
import com.hbs.dto.UserDTO;
import com.hbs.entities.User;
import com.hbs.exceptions.AdminAlreadyExistsException;
import com.hbs.exceptions.AdminNotFoundException;
import com.hbs.service.AdminService;
import com.hbs.util.MapperUtil;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService service;

	@PostMapping
	public ResponseEntity<UserDTO> add(@Valid @RequestBody UserDTO userDto) throws AdminAlreadyExistsException {
		User user = MapperUtil.mapToUser(userDto);
		return new ResponseEntity<>(MapperUtil.mapToUserDto(service.add(user)), HttpStatus.OK);
	}

	@PostMapping("/signout")
	public ResponseEntity<UserDTO> signOut(@RequestBody JwtRequest jwtRequest) throws AdminNotFoundException {
		return new ResponseEntity<>(MapperUtil.mapToUserDto(service.signOut(jwtRequest)), HttpStatus.OK);
	}
}
