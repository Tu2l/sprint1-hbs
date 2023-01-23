package com.hbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbs.dto.TransactionsDTO;
import com.hbs.entities.Transactions;
import com.hbs.repository.TransactionRepository;
import com.hbs.util.MapperUtil;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	public TransactionsDTO add(TransactionsDTO dto) {
	    Transactions transactions = MapperUtil.mapToTransaction(dto);
	    if(transactions==null||transactions.getAmount()<0) return null;
	    return MapperUtil.mapToTransactionDto(transactionRepository.save(transactions));
	}


}
