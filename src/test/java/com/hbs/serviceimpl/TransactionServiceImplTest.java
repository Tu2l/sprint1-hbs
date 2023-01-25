package com.hbs.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hbs.dto.TransactionsDTO;
import com.hbs.entities.Transactions;
import com.hbs.repository.TransactionRepository;
import com.hbs.util.MapperUtil;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

	@Mock
	private TransactionRepository transactionRepoMock;

	@InjectMocks
	private TransactionServiceImpl serviceMock;
	private MockedStatic<MapperUtil> mockedUtil;

	private TransactionsDTO dto;
	private Transactions trans;

	@BeforeEach
	void setup() {
		dto = new TransactionsDTO();
		dto.setTransactionId(1910);
		dto.setAmount(19000);
		
		trans = MapperUtil.mapToTransaction(dto);		
	}
	
	@AfterEach
	void close() {
		
	}

	
	@Test
	void testAdd() {
		mockedUtil = mockStatic(MapperUtil.class);
		when(MapperUtil.mapToTransactionDto(trans)).thenReturn(dto);
		when(MapperUtil.mapToTransaction(dto)).thenReturn(trans);
		when(serviceMock.add(dto)).thenReturn(dto);
		assertEquals(19000, serviceMock.add(dto).getAmount());
		mockedUtil.close();
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
