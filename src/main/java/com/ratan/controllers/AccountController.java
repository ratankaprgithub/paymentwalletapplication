package com.ratan.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratan.model.BankAccount;
import com.ratan.service.BankAccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private BankAccountService bankService;
	
	
	/*
	 *  {
        "ifscCode": "sbiifsc",
        "bankName": "sbi",
        "balance": 60000.0
    }
	 * 
	 * 
	 */
	@PostMapping("/createAccount")
	public BankAccount createAccount(@RequestBody BankAccount account) {
		
		return bankService.addAccount(account);
	}
	
	@GetMapping("/getAllAccounts")
	public List<BankAccount> getAllBankAccounts(){
		return bankService.viewAllBankAccounts();
	}
	
	@GetMapping("/getAccount/{accno}")
	public BankAccount getAccountByAccountNumber(@PathVariable int accno) {
	
		return bankService.viewAccount(accno);
	}
	
	
	@DeleteMapping("/deleteAccount/{accno}")
	public BankAccount deleteAccount(@PathVariable int accno) {
		return bankService.removeBankAccount(accno);
	}
	
	
	
	
	
	
	
}
