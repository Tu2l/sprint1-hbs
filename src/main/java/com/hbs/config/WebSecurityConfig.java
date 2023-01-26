package com.hbs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hbs.auth.JwtAuthenticationEntryPoint;
import com.hbs.auth.JwtFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter filter,
			JwtAuthenticationEntryPoint authenticationEntryPoint) throws Exception {
		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/swagger-ui.html", "/configuration/security", "/configuration/ui", "/v2/api-docs",
						"/swagger-resources/**", "/swagger-ui/**", "/webjars/**", "/auth/**")
				.permitAll()
				.antMatchers(HttpMethod.GET, "/hotel", "/hotel/**", "/room", "/room/**").permitAll()
				.antMatchers(HttpMethod.POST, "/bookings").hasRole("USER")
				.antMatchers(HttpMethod.GET, "/bookings/user/**").hasRole("USER")
				.antMatchers(HttpMethod.PUT, "/user/{id}").hasRole("USER")
				.antMatchers("/**").hasRole("ADMIN")
				.anyRequest().authenticated();

		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
