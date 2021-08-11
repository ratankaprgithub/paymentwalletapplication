package com.ratan.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratan.exception.NoTransactionFoundException;
import com.ratan.model.TransactionBean;
import com.ratan.repositories.TransactionRepo;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepo transactionRepo;
	
	
	
	@Override
	public List<TransactionBean> viewAllTransactionForCustomer(String mobileNo) {
		
		List<TransactionBean> list= transactionRepo.findByMobileNo(mobileNo);
		
		if(list.size() ==0)
			throw new NoTransactionFoundException("No transaction found for the mobile number "+mobileNo);
		
		return list;
		
		
	}

	@Override
	public List<TransactionBean> viewTransactionForCustomerByDate(LocalDate startDate, LocalDate endDate) {
		
		return transactionRepo.findByTransactionDateBetween(startDate, endDate);
		
	}

	@Override
	public TransactionBean viewTheTransactionById(int txId) {
		
	return transactionRepo.findById(txId).orElseThrow(() -> new NoTransactionFoundException("Invalid Transaction Ids"));
		
		
	}

}
