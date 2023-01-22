package com.hbs.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hbs.entities.User;
import com.hbs.entities.UserRole;
import com.hbs.exceptions.UserNotFoundException;
import com.hbs.service.UserService;

import lombok.Setter;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	private UserService userService;

	private @Setter UserRole role = UserRole.UNDEFINED;

	public JwtUserDetailsService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			if (role == UserRole.UNDEFINED)
				throw new UserNotFoundException("Role not defined");

			User user = userService.findByEmail(email);
			
			List<GrantedAuthority> roles = new ArrayList<>();
			roles.add(new SimpleGrantedAuthority("ROLE_" + role));
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), roles);
		} catch (UserNotFoundException e) {
			throw new UsernameNotFoundException("User not found with username: " + email);
		}
	}
}
