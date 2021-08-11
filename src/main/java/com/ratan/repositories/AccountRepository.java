package com.ratan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ratan.model.BankAccount;


public interface AccountRepository extends JpaRepository<BankAccount, Integer> {

	
	
	
	
}
