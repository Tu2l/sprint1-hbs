package com.hbs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hbs.entities.User;
import com.hbs.entities.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
	
	Optional<User> findByMobile(String mobile);

	List<User> findAllByRole(UserRole user);

	Optional<User> findByUserIdAndRole(int userId, UserRole user);

	Optional<User> findByEmailAndRole(String email, UserRole user);
	
	User findByUserName(String userName);
}
