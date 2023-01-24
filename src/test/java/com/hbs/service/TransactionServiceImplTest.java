package com.hbs.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hbs.dto.TransactionsDTO;
import com.hbs.entities.Transactions;
import com.hbs.repository.TransactionRepository;
import com.hbs.serviceimpl.TransactionServiceImpl;

class TransactionServiceImplTest {

	@Mock
	private TransactionRepository transactionRepoMock;

	@InjectMocks
	private TransactionServiceImpl serviceMock;

	private TransactionsDTO transaction;

	@BeforeEach
	public void setup() {
		serviceMock=new TransactionServiceImpl();
		transaction = new TransactionsDTO();
		transaction.setTransactionId(1910);
		transaction.setAmount(19000);
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testAddTransactionFailure() {
	    transaction.setTransactionId(1910);
	    transaction.setAmount(-19000);
	    TransactionsDTO result = serviceMock.add(transaction);
	    assertNull(result);
	    verify(transactionRepoMock, never()).save(any(Transactions.class));
	}


}
