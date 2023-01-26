package com.hbs.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbs.dto.TransactionsDTO;
import com.hbs.repository.TransactionRepository;
import com.hbs.service.TransactionService;
import com.hbs.util.MapperUtil;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public TransactionsDTO add(TransactionsDTO dto) {
		if (dto == null || dto.getAmount() < 0)
			return null;
	    return MapperUtil.mapToTransactionDto(transactionRepository.save(MapperUtil.mapToTransaction(dto)));
	}


}
