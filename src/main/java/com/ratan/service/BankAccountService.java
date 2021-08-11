package com.ratan.service;

import java.util.List;

import com.ratan.model.BankAccount;

public interface BankAccountService {
	
	public BankAccount addAccount(BankAccount bankAccount);
	
	public BankAccount removeBankAccount(int accno);
	
	public BankAccount viewAccount(int accno);
	
	public List<BankAccount> viewAllBankAccounts();

}
