package com.hbs.service;

import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.hbs.auth.JwtRequest;
import com.hbs.auth.JwtResponse;
import com.hbs.entities.JwtToken;
import com.hbs.entities.UserRole;
import com.hbs.exceptions.AdminNotFoundException;
import com.hbs.exceptions.InvalidCredentialsException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public interface JwtService {
	long TOKEN_VALIDITY = TimeUnit.DAYS.toMillis(30);
	String SECRET = "hbs";
	String ENCODED_JWT_SECRECT = Base64.getEncoder().encodeToString(SECRET.getBytes());

	default String generate(Map<String, Object> claims, String username) {
		return Jwts.builder().setClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
				.signWith(SignatureAlgorithm.HS512, ENCODED_JWT_SECRECT).compact();
	}

	default String getUsernameFromToken(String token) {
		final Claims claims = Jwts.parser().setSigningKey(ENCODED_JWT_SECRECT).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	default UserRole getRoleFromToken(String token) {
		final Claims claims = Jwts.parser().setSigningKey(ENCODED_JWT_SECRECT).parseClaimsJws(token).getBody();
		return UserRole.set(String.valueOf(claims.get("role")));
	}

	JwtToken generateToken(String email, UserRole role);

	Boolean validateJwtToken(String token, String email) throws InvalidCredentialsException;

	JwtResponse authenticate(JwtRequest request, UserRole role)
			throws AdminNotFoundException, InvalidCredentialsException;

	boolean invalidateToken(String username);

	JwtToken add(JwtToken token);

	JwtToken deleteByToken(String token) throws InvalidCredentialsException;

	JwtToken findByEmail(String email) throws InvalidCredentialsException;

}
