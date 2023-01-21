package com.hbs.auth;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hbs.exceptions.AdminNotFoundException;
import com.hbs.exceptions.InvalidCredentialsException;

@RestController
@CrossOrigin
public class JwtController {
	@Autowired
	private JwtUserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenManager tokenManager;

	@PostMapping("/login")
	public ResponseEntity createToken(@Valid @RequestBody JwtRequestModel request) throws Exception {
		try {
			System.out.println(request);
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
		} catch (DisabledException e) {
			throw new AdminNotFoundException("USER_DISABLED");
		} catch (BadCredentialsException e) {
			throw new InvalidCredentialsException("INVALID_CREDENTIALS");
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
		final String jwtToken = tokenManager.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponseModel(jwtToken));
	}
}