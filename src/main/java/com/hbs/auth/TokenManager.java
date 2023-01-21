package com.hbs.auth;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenManager {
	public static final long TOKEN_VALIDITY = 10 * 60 * 60;

//	@Value("${secret}")
	private final String SECRET = "hbs";
	private final String ENCODED_JWT_SECRECT;

	public TokenManager() {
		ENCODED_JWT_SECRECT = Base64.getEncoder().encodeToString(SECRET.getBytes());
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, ENCODED_JWT_SECRECT).compact();
	}

	public Boolean validateJwtToken(String token, UserDetails userDetails) {
		String username = getUsernameFromToken(token);
		Claims claims = Jwts.parser().setSigningKey(ENCODED_JWT_SECRECT).parseClaimsJws(token).getBody();
		Boolean isTokenExpired = claims.getExpiration().before(new Date());
		return (username.equals(userDetails.getUsername()) && !isTokenExpired);
	}

	public String getUsernameFromToken(String token) {
		final Claims claims = Jwts.parser().setSigningKey(ENCODED_JWT_SECRECT).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
}
