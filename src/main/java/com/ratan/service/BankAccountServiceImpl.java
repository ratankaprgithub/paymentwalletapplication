package com.ratan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratan.exception.AccountNotFoundException;
import com.ratan.model.BankAccount;
import com.ratan.repositories.AccountRepository;

@Service
public class BankAccountServiceImpl implements BankAccountService{

	@Autowired
	private AccountRepository accountRepo;
	
	
	@Override
	public BankAccount addAccount(BankAccount bankAccount) {
		
		return accountRepo.save(bankAccount);
	}

	@Override
	public BankAccount removeBankAccount(int accno) {
		
		
		BankAccount account= accountRepo.findById(accno).orElseThrow(()-> new AccountNotFoundException("Account does not exist") );
		
		accountRepo.delete(account);

		return account;
		
		
	}

	@Override
	public BankAccount viewAccount(int accno) {
		
		System.out.println(accno);
		
	 	return accountRepo.findById(accno).orElseThrow(() -> new AccountNotFoundException("Account does not exist"));
		
	}

	@Override
	public List<BankAccount> viewAllBankAccounts() {
	
		return accountRepo.findAll();
	}

}
