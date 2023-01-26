package com.hbs.auth;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hbs.exceptions.InvalidCredentialsException;
import com.hbs.service.JwtService;
import com.hbs.util.LoggerUtil;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUserDetailsService userDetailsService;
	@Autowired
	private JwtService service;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String tokenHeader = request.getHeader("Authorization");
		String username = null;
		String token = null;
		if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
			token = tokenHeader.substring(7);
			try {
				LoggerUtil.logInfo(token);
				username = service.getUsernameFromToken(token);
			} catch (IllegalArgumentException e) {
				LoggerUtil.logInfo("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				LoggerUtil.logInfo("JWT Token has expired");
			}
		} else {
			LoggerUtil.logInfo("Bearer String not found in token");
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			userDetailsService.setRole(service.getRoleFromToken(token));
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			try {
				if (Boolean.TRUE.equals(service.validateJwtToken(token, userDetails.getUsername()))) {
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}
			} catch (InvalidCredentialsException e) {
				LoggerUtil.logInfo(e.getMessage());
			}
		}
		filterChain.doFilter(request, response);
	}
}