package com.hbs.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hbs.entities.Transactions;
import com.hbs.repository.TransactionRepository;

class TransactionServiceImplTest {

	@Mock
	private TransactionRepository transactionRepoMock;

	@InjectMocks
	private TransactionServiceImpl serviceMock;

	private Transactions transaction;

	@BeforeEach
	public void setup() {
		serviceMock=new TransactionServiceImpl();
		transaction = new Transactions();
		transaction.setTransactionId(1910);
		transaction.setAmount(19000);
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAdd() {
		
		when(serviceMock.add(transaction)).thenReturn(transaction);
		Transactions returnedTransaction = serviceMock.add(transaction);
		assertEquals(transaction, returnedTransaction);
		verify(transactionRepoMock, times(1)).save(transaction);
	}

}
