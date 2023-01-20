package com.hbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbs.entities.Admin;
import com.hbs.exceptions.AdminAlreadyExistsException;
import com.hbs.exceptions.AdminNotFoundException;
import com.hbs.exceptions.InvalidCredentialsException;
import com.hbs.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService service;

	@PostMapping
	public ResponseEntity<Admin> add(@RequestBody Admin admin) throws AdminAlreadyExistsException {
		return new ResponseEntity<>(service.add(admin), HttpStatus.OK);
	}

	@PostMapping("/signin")
	public ResponseEntity<Admin> signIn(@RequestBody Admin admin)
			throws AdminNotFoundException, InvalidCredentialsException {
		return new ResponseEntity<>(service.signIn(admin), HttpStatus.OK);
	}

	@PostMapping("/signout")
	public ResponseEntity<Admin> signOut(@RequestBody Admin admin) {
		return new ResponseEntity<>(service.signOut(admin), HttpStatus.OK);
	}

}
