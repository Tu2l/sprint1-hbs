package com.hbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hbs.auth.JwtRequest;
import com.hbs.auth.JwtResponse;
import com.hbs.entities.User;
import com.hbs.entities.UserRole;
import com.hbs.exceptions.AdminAlreadyExistsException;
import com.hbs.exceptions.AdminNotFoundException;
import com.hbs.exceptions.InvalidCredentialsException;
import com.hbs.repository.UserRepository;

@Service
public class AdminServiceImpl implements AdminService {
	private static final String ADMIN_NOT_FOUND_MESSAGE = "Admin not found with email ";
	private static final String ADMIN_ALREADY_EXISTS = "Admin already exists";

	@Autowired
	private UserRepository repo;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public JwtResponse signIn(JwtRequest request) throws AdminNotFoundException, InvalidCredentialsException {
		return jwtService.authenticate(request, UserRole.ADMIN);
	}

	@Override
	public User signOut(JwtRequest request) throws AdminNotFoundException {
		User find = findByEmail(request.getEmail());
		jwtService.invalidateToken(request.getEmail());
		return find;
	}

	@Override
	public User add(User admin) throws AdminAlreadyExistsException {
		if (repo.findByEmail(admin.getEmail()).isPresent())
			throw new AdminAlreadyExistsException(ADMIN_ALREADY_EXISTS);

		admin.setRole(UserRole.ADMIN);
		admin.setPassword(encoder.encode(admin.getPassword()));
		return repo.save(admin);
	}

	@Override
	public User findByEmail(String email) throws AdminNotFoundException {
		return repo.findByEmail(email).orElseThrow(() -> new AdminNotFoundException(ADMIN_NOT_FOUND_MESSAGE + email));
	}

}
