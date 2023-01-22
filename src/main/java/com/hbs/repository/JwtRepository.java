package com.hbs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hbs.entities.JwtToken;

@Repository
public interface JwtRepository extends JpaRepository<JwtToken, Integer> {
	void deleteByEmail(String email);
	
	void deleteByToken(String token);
	
	Optional<JwtToken> findByToken(String token);
	
	Optional<JwtToken> findByEmail(String email);
}
