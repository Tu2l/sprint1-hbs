package com.hbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbs.entities.Transactions;
import com.hbs.repository.ITransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private ITransactionRepository transactionRepository;

	@Override
	public Transactions addTransaction(Transactions transaction) {

		return transactionRepository.save(transaction);
	}

}
