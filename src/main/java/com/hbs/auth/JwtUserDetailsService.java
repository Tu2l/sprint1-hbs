package com.hbs.auth;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hbs.entities.Admin;
import com.hbs.exceptions.AdminNotFoundException;
import com.hbs.service.AdminService;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private AdminService adminService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			Admin admin = adminService.findByEmail(email);
			return new User(admin.getEmail(), admin.getPassword(), new ArrayList<>());
		} catch (AdminNotFoundException e) {
			throw new UsernameNotFoundException("User not found with username: " + email);
		}
	}
}
