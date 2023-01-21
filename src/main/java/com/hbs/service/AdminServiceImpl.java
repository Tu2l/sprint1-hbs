package com.hbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hbs.entities.Admin;
import com.hbs.exceptions.AdminAlreadyExistsException;
import com.hbs.exceptions.AdminNotFoundException;
import com.hbs.exceptions.InvalidCredentialsException;
import com.hbs.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
	private static final String ADMIN_NOT_FOUND_MESSAGE = "Admin not found with email ";
	private static final String INVALID_CREDENTIALS_MESSAGE = "Email or password is wrong";
	private static final String ADMIN_ALREADY_EXISTS = "Admin already exists";

	@Autowired
	private AdminRepository repo;


	private PasswordEncoder encoder = new BCryptPasswordEncoder();

	@Override
	public Admin signIn(Admin admin) throws AdminNotFoundException, InvalidCredentialsException {
		Admin find = repo.findByEmail(admin.getEmail());
		if (find == null)
			throw new AdminNotFoundException(ADMIN_NOT_FOUND_MESSAGE + admin.getEmail());

		// verify hashed password
		String findPass = encoder.encode(find.getPassword());
		String pass = encoder.encode(admin.getPassword());
		if (findPass.equals(pass))
			return find;

		throw new InvalidCredentialsException(INVALID_CREDENTIALS_MESSAGE);
	}

	@Override
	public Admin signOut(Admin admin) {
		return admin;
	}

	@Override
	public Admin add(Admin admin) throws AdminAlreadyExistsException {
		Admin find = repo.findByEmail(admin.getEmail());
		if (find != null)
			throw new AdminAlreadyExistsException(ADMIN_ALREADY_EXISTS);

		admin.setPassword(encoder.encode(admin.getPassword()));
		return repo.save(admin);
	}

	@Override
	public Admin findByEmail(String email) throws AdminNotFoundException {
		Admin admin = repo.findByEmail(email);
		if (admin == null)
			throw new AdminNotFoundException(ADMIN_NOT_FOUND_MESSAGE + email);
		return repo.findByEmail(email);
	}

}
