package com.hbs.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hbs.dto.TransactionsDTO;
import com.hbs.entities.Transactions;
import com.hbs.repository.TransactionRepository;
import com.hbs.serviceimpl.TransactionServiceImpl;
import com.hbs.util.MapperUtil;

class TransactionServiceImplTest {

	@Mock
	private TransactionRepository transactionRepoMock;

	@InjectMocks
	private TransactionServiceImpl serviceMock;

	private TransactionsDTO dto;

	@BeforeEach
	public void setup() {

		dto = new TransactionsDTO();
		dto.setTransactionId(1910);
		dto.setAmount(19000);
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAdd() {
		mockStatic(MapperUtil.class);
		when(serviceMock.add(dto)).thenReturn(dto);
		assertEquals(19000, serviceMock.add(dto).getAmount());
	}

	@Test
	void testAddTransactionFailure() {
		dto.setTransactionId(1910);
		dto.setAmount(-19000);
		TransactionsDTO result = serviceMock.add(dto);
		assertNull(result);
		verify(transactionRepoMock, never()).save(any(Transactions.class));
	}

}
