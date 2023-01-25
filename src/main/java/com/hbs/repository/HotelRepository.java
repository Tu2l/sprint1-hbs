package com.hbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hbs.entities.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
	Hotel findByEmail(String email);
}
