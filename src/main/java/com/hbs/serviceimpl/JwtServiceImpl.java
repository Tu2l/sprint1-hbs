package com.hbs.serviceimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.hbs.auth.JwtRequest;
import com.hbs.auth.JwtResponse;
import com.hbs.auth.JwtUserDetailsService;
import com.hbs.entities.JwtToken;
import com.hbs.entities.UserRole;
import com.hbs.exceptions.InvalidCredentialsException;
import com.hbs.repository.JwtRepository;
import com.hbs.service.JwtService;
import com.hbs.util.MapperUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtServiceImpl implements JwtService {
	private static final String INVALID_CREDENTIALS_MESSAGE = "Email or password is wrong";
	private static final String INVALID_TOKEN_MESSAGE = "Invalid token";
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	@Autowired
	@Lazy
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtRepository repo;

	@Override
	public JwtResponse authenticate(JwtRequest request, UserRole role) throws InvalidCredentialsException {
		userDetailsService.setRole(role);
		try {			
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(request.getEmail(),
					request.getPassword());
		
			authenticationManager.authenticate(auth);
		} catch (BadCredentialsException e) {
		
			throw new InvalidCredentialsException(INVALID_CREDENTIALS_MESSAGE);
		}


		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		final JwtToken jwt = generateToken(userDetails.getUsername(), role);
		return MapperUtil.mapToJwtResponse(jwt);
	}

	@Override
	public boolean invalidateToken(String email) {
		Optional<JwtToken> jwt = repo.findByEmail(email);
		if(jwt.isPresent())
			repo.delete(jwt.get());
		return true;
	}

	@Override
	public JwtToken add(JwtToken token) {
		invalidateToken(token.getEmail());
		
		return repo.save(token);
	}

	@Override
	public JwtToken deleteByToken(String token) throws InvalidCredentialsException {
		JwtToken jwt = repo.findByToken(token)
				.orElseThrow(() -> new InvalidCredentialsException(INVALID_TOKEN_MESSAGE));
		repo.delete(jwt);
		
		return jwt;
	}

	@Override
	public JwtToken findByEmail(String email) throws InvalidCredentialsException {
		return repo.findByEmail(email).orElseThrow(() -> new InvalidCredentialsException(INVALID_TOKEN_MESSAGE));
	}

	@Override
	public JwtToken generateToken(String email, UserRole role) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", role);
		String token = generate(claims, email);

		JwtToken jwt = new JwtToken();
		jwt.setToken(token);
		jwt.setRole(role);
		jwt.setEmail(email);

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
