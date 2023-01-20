package com.hbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hbs.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
	
}
