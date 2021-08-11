package com.ratan.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratan.model.Customer;
import com.ratan.service.WalletService;

@RestController
@RequestMapping("/wallet")
public class WalletController {

	@Autowired
	private WalletService walletService;
	
	/*
	{
	    "name": "Rama",
	    "mobileNo": "3232324",
	    "password": "123",
	    "wallet":{
	            "balance":5000
	    }
	}
	*/
	@PostMapping("/createWalletAccount")
	public Customer createCustomerAccount(@RequestBody Customer customer) {

		return walletService.createAccount(customer);		
	}
	
	@GetMapping("/getBalance/{mobileNo}")
	public BigDecimal getCustomerBalance(@PathVariable String mobileNo) {
		
		return walletService.showBalance(mobileNo);
	}
	
	
	@PutMapping("/depositAmount/{mobileNo}/{amount}")
	public Customer depositAmount(@PathVariable String mobileNo,@PathVariable BigDecimal amount) {
		
		return walletService.depositAmount(mobileNo, amount);
		
	}
	
	
	@GetMapping("/getAllCustomer")
	public List<Customer> getAllCustomer(){
		return walletService.getAllCustomerList();
	}
	
	@PutMapping("/addMoneyFromAccount/{mobileNo}/{accno}/{amount}")
	public Customer addMoneyFromAccount(@PathVariable String mobileNo,
										@PathVariable int accno,
										@PathVariable int amount) {
		
		return walletService.addMoneyToWalletFromAccount(mobileNo, accno, amount);
		

	}
	
	
	@PutMapping("/transferAmount/{sourceMobileNo}/{targetMobileNo}/{amount}")
	public Customer transferFund(@PathVariable String sourceMobileNo,
								@PathVariable String targetMobileNo,
								@PathVariable BigDecimal amount) {
		
		return walletService.fundTransfer(sourceMobileNo, targetMobileNo, amount);
		
		
		
	}
	
	
	
	
	
	
	
	
}
