package com.hbs.repository;

import com.hbs.entities.Transactions;

public interface ITransactionRepository {
	public Transactions addTransaction(Transactions transaction);
}
