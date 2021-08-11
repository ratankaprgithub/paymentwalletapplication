package com.ratan.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.JodaTimeConverters.DateTimeToDateConverter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratan.model.TransactionBean;
import com.ratan.service.TransactionService;

@RestController
@RequestMapping("/txn")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	
	@GetMapping("/getAllTransactions/{mobileNo}")
	public List<TransactionBean> getAllTheTransactionForCustomer(@PathVariable String mobileNo){
		return transactionService.viewAllTransactionForCustomer(mobileNo);
	}
	
	
	@GetMapping("getTransactionById/{txId}")
	public TransactionBean getTransactionById(@PathVariable int txId) {
		
		return transactionService.viewTheTransactionById(txId);
	}
	
	@GetMapping("/getTransactionBetweenDates/{sdate}/{edate}")
	public List<TransactionBean> getAllTransactionsBetweenDates(
									@PathVariable String sdate,
									@PathVariable String edate){
		
		
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		LocalDate ssdate  =LocalDate.parse(sdate,dtf);
		LocalDate eedate  =LocalDate.parse(edate,dtf);
		
		
		return transactionService.viewTransactionForCustomerByDate(ssdate, eedate);
		
	}
	
	
	
	
}
