package com.hbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hbs.entities.Transactions;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Integer> {
	
}
