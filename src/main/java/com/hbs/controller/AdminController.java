package com.hbs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbs.dto.AdminDTO;
import com.hbs.entities.Admin;
import com.hbs.exceptions.AdminAlreadyExistsException;
import com.hbs.exceptions.AdminNotFoundException;
import com.hbs.exceptions.InvalidCredentialsException;
import com.hbs.service.AdminService;
import com.hbs.util.MapperUtil;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService service;

	@PostMapping
	public ResponseEntity<AdminDTO> add(@Valid @RequestBody AdminDTO adminDto) throws AdminAlreadyExistsException {
		Admin admin = MapperUtil.mapToAdmin(adminDto);
		return new ResponseEntity<>(MapperUtil.mapToAdminDto(service.add(admin)), HttpStatus.OK);
	}

	@PostMapping("/signin")
	public ResponseEntity<AdminDTO> signIn(@Valid @RequestBody AdminDTO adminDto)
			throws AdminNotFoundException, InvalidCredentialsException {
		Admin admin = MapperUtil.mapToAdmin(adminDto);
		return new ResponseEntity<>(MapperUtil.mapToAdminDto(service.signIn(admin)), HttpStatus.OK);
	}

	@PostMapping("/signout")
	public ResponseEntity<AdminDTO> signOut(@RequestBody AdminDTO adminDto) {
		Admin admin = MapperUtil.mapToAdmin(adminDto);
		return new ResponseEntity<>(MapperUtil.mapToAdminDto(admin), HttpStatus.OK);
	}
}
