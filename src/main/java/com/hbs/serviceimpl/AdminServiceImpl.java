package com.hbs.serviceimpl;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hbs.auth.JwtRequest;
import com.hbs.auth.JwtResponse;
import com.hbs.dto.UserDTO;
import com.hbs.entities.User;
import com.hbs.entities.UserRole;
import com.hbs.exceptions.AdminAlreadyExistsException;
import com.hbs.exceptions.AdminNotFoundException;
import com.hbs.exceptions.InvalidCredentialsException;
import com.hbs.repository.UserRepository;
import com.hbs.service.AdminService;
import com.hbs.service.JwtService;
import com.hbs.util.MapperUtil;

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
	public UserDTO signOut(JwtRequest request) throws AdminNotFoundException {
		UserDTO find = findByEmail(request.getEmail());
		jwtService.invalidateToken(request.getEmail());
		return find;
	}

	@Override
	public UserDTO add(UserDTO dto) throws AdminAlreadyExistsException {
		Optional<User> find = repo.findByEmail(dto.getEmail());
		if (find.isPresent())
			throw new AdminAlreadyExistsException(ADMIN_ALREADY_EXISTS);
  
	
		User admin = MapperUtil.mapToUser(dto);
		admin.setRole(UserRole.ADMIN);
		admin.setPassword(encoder.encode(admin.getPassword()));
		return MapperUtil.mapToUserDto(repo.save(admin));
	}

	@Override
	public UserDTO findByEmail(String email) throws AdminNotFoundException {
		User admin =repo.findByEmail(email).orElseThrow(() -> new AdminNotFoundException(ADMIN_NOT_FOUND_MESSAGE + email));
		return MapperUtil.mapToUserDto(admin);
	}
	
	
}
