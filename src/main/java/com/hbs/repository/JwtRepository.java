package com.hbs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hbs.entities.JwtToken;

@Repository
public interface JwtRepository extends JpaRepository<JwtToken, Integer> {
	void deleteByToken(String token);
	
	Optional<JwtToken> findByToken(String token);
	
//	@Query("select jwt from JwtToken jwt where jwt.email=:email")
	Optional<JwtToken> findByEmail(@Param("email")String email);
}
