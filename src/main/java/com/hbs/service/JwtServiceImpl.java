package com.hbs.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.hbs.auth.JwtRequest;
import com.hbs.auth.JwtResponse;
import com.hbs.auth.JwtUserDetailsService;
import com.hbs.entities.JwtToken;
import com.hbs.entities.User;
import com.hbs.entities.UserRole;
import com.hbs.exceptions.InvalidCredentialsException;
import com.hbs.repository.JwtRepository;
import com.hbs.util.MapperUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtServiceImpl implements JwtService {

	@Autowired
	private JwtUserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtRepository repo;

	@Override
	public JwtResponse authenticate(JwtRequest request, UserRole role) throws InvalidCredentialsException {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException e) {
			throw new InvalidCredentialsException("INVALID_CREDENTIALS");
		}

		userDetailsService.setRole(role);
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		final JwtToken jwt = generateToken(userDetails.getUsername(), role);
		return MapperUtil.mapToJwtResponse(jwt);
	}

	@Override
	public boolean invalidateToken(String email) {
		repo.deleteByEmail(email);
		return repo.findByEmail(email).isEmpty();
	}

	@Override
	public JwtToken add(JwtToken token) {
		repo.deleteByEmail(token.getUser().getEmail());
		return repo.save(token);
	}

	@Override
	public JwtToken deleteByToken(String token) throws InvalidCredentialsException {
		JwtToken jwt = repo.findByToken(token).orElseThrow(() -> new InvalidCredentialsException("Token not found"));
		repo.deleteByEmail(token);
		return jwt;
	}

	@Override
	public JwtToken findByEmail(String email) throws InvalidCredentialsException {
		return repo.findByToken(email).orElseThrow(() -> new InvalidCredentialsException("Token not found"));
	}

	@Override
	public JwtToken generateToken(String email, UserRole role) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", role);
		String token = generate(claims, email);

		JwtToken jwt = new JwtToken();
		jwt.setToken(token);
		jwt.setRole(role);
		User user = new User();
		user.setEmail(email);
		jwt.setUser(user);

		return add(jwt);
	}

	@Override
	public Boolean validateJwtToken(String token, String email) throws InvalidCredentialsException {
		String tokenEmail = getUsernameFromToken(token);
		Claims claims = Jwts.parser().setSigningKey(ENCODED_JWT_SECRECT).parseClaimsJws(token).getBody();
		Boolean isTokenExpired = claims.getExpiration().before(new Date());
		UserRole role = UserRole.set(String.valueOf(claims.get("role")));
		JwtToken jwt = findByEmail(email);

		return (tokenEmail.equals(email) && !isTokenExpired && role == jwt.getRole());
	}

}
