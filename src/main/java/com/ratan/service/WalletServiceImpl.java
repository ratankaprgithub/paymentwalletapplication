package com.ratan.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ratan.exception.AccountNotFoundException;
import com.ratan.exception.CustomerNotFoundException;
import com.ratan.exception.InsufficientAmountException;
import com.ratan.model.BankAccount;
import com.ratan.model.Customer;
import com.ratan.model.TransactionBean;
import com.ratan.model.Wallet;
import com.ratan.repositories.AccountRepository;
import com.ratan.repositories.TransactionRepo;
import com.ratan.repositories.UserRepository;

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private TransactionRepo transactionRepo;
	
	
	@Override
	public Customer createAccount(Customer customer) {
		
		return userRepo.save(customer);
		
	}

	@Override
	public BigDecimal showBalance(String mobileNo) {
		
		Customer customer= userRepo.findByMobileNo(mobileNo);
		
		if(customer == null)
			throw new CustomerNotFoundException("Customer not found with Mobile number" +mobileNo);
		else
			return customer.getWallet().getBalance();
		
	}

	@Transactional
	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) {
		
		Customer customer= userRepo.findByMobileNo(mobileNo);
		
		if(customer == null) 
			throw new CustomerNotFoundException("Customer Not found with mobile number"+mobileNo);
		else
		{
			Wallet w= customer.getWallet();
			
			w.setBalance(w.getBalance().add(amount));
			
			customer.setWallet(w);
			
			TransactionBean tr=new TransactionBean();
			tr.setAmount(amount.doubleValue());
			tr.setTransactionType("Amount deposited"+amount);
			tr.setMobileNo(mobileNo);
			tr.setTransactionDate(LocalDate.now());
			tr.setDescription(amount+" Amount Deposited to "+customer.getName()+" wallet");
			
			transactionRepo.save(tr);
			
			
			return userRepo.save(customer);
			
			
		}
		
		
		
		
	}

	@Override
	public List<Customer> getAllCustomerList() {
		
		return userRepo.findAll();
	}

	@Transactional
	@Override
	public Customer addMoneyToWalletFromAccount(String mobileNo, int accno,int amount) {
		
		BankAccount acc= accountRepo.findById(accno).orElseThrow(() -> new AccountNotFoundException("Account not found with given Account number"+accno));
		
		Customer customer= userRepo.findByMobileNo(mobileNo);
		
		if(customer == null)
			throw new CustomerNotFoundException("Customer not found with Mobileno :"+mobileNo);
		

		
		if(acc.getBalance() >= amount) {
			
			acc.setBalance(acc.getBalance()-amount);
			Wallet w=customer.getWallet();
			w.setBalance(w.getBalance().add(BigDecimal.valueOf(amount)));
			
			customer.setWallet(w);
			
			accountRepo.save(acc);
			
			
			
			TransactionBean tr=new TransactionBean();
			tr.setAmount(amount);
			tr.setTransactionType("Amount Transfered "+amount);
			tr.setMobileNo(mobileNo);
			tr.setTransactionDate(LocalDate.now());
			tr.setDescription(amount+" Amount Transfered from Bank to the Wallet ");
			
			transactionRepo.save(tr);
			
			
			return userRepo.save(customer);
			
			
		}
		else
			throw new InsufficientAmountException("Insufficient Balance...");
		
	}

	
	@Transactional
	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) {
		
		Customer source= userRepo.findByMobileNo(sourceMobileNo);
		Customer target= userRepo.findByMobileNo(targetMobileNo);
		
		if(source == null)
			throw new CustomerNotFoundException("Source Customer Not Found..");
		
		if(target == null)
			throw new CustomerNotFoundException("Target Customer Not Found..");
		
		Wallet swallet= source.getWallet();
		
		if(swallet.getBalance().compareTo(amount) >=0 ) {
			
			Wallet twallet= target.getWallet();
			
			swallet.setBalance(swallet.getBalance().subtract(amount));
			twallet.setBalance(twallet.getBalance().add(amount));
			
			source.setWallet(swallet);
			target.setWallet(twallet);
			
			userRepo.save(target);
		
			
			TransactionBean tr=new TransactionBean();
			tr.setAmount(amount.doubleValue());
			tr.setTransactionType("Amount Transfered "+amount);
			tr.setMobileNo(sourceMobileNo);
			tr.setTransactionDate(LocalDate.now());
			tr.setDescription(amount+" transfered from "+sourceMobileNo +" to the "+targetMobileNo);
			
			transactionRepo.save(tr);
			

			return userRepo.save(source);
			
			
		}
		else
			throw new InsufficientAmountException("Insufficient Amount in Source Wallet");
		
	
	}

}
