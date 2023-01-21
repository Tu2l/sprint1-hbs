package com.hbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hbs.entities.Payments;

@Repository
public interface PaymentRepository extends JpaRepository<Payments, Integer> {
	
}
