package com.ratan.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ratan.model.TransactionBean;

public interface TransactionRepo extends JpaRepository<TransactionBean, Integer>{

	public List<TransactionBean> findByMobileNo(String mobileNo);
	
	public List<TransactionBean> findByTransactionDateBetween(LocalDate start,LocalDate end);
}
