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
import com.hbs.auth.JwtResponse;
import com.hbs.exceptions.AdminNotFoundException;
import com.hbs.exceptions.InvalidCredentialsException;
import com.hbs.exceptions.UserNotFoundException;
import com.hbs.service.AdminService;
import com.hbs.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;

	@PostMapping("/admin/signin")
	public ResponseEntity<JwtResponse> adminSignIn(@Valid @RequestBody JwtRequest jwtRequest)
			throws AdminNotFoundException, InvalidCredentialsException {
		return new ResponseEntity<>(adminService.signIn(jwtRequest), HttpStatus.OK);
	}

	@PostMapping("/user/signin")
	public ResponseEntity<JwtResponse> userSignIn(@Valid @RequestBody JwtRequest jwtRequest)
			throws InvalidCredentialsException, UserNotFoundException {
		return new ResponseEntity<>(userService.signIn(jwtRequest), HttpStatus.OK);
	}

}
