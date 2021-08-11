package com.ratan.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratan.model.BillPayment;
import com.ratan.model.BillType;
import com.ratan.service.BillPaymentService;

@RestController
@RequestMapping("/bill")
public class BillPaymentController {

	@Autowired
	private BillPaymentService billService;
	
	
	//http://localhost:8000/bill/payTheBill/3232325/GAS/700
	
	@PostMapping("/payTheBill/{mobileNo}/{billType}/{amount}")
	public BillPayment payTheBill(@PathVariable String mobileNo,
								@PathVariable BillType billType,
								@PathVariable double amount) {
		
		return billService.payTheBill(mobileNo,billType , amount);
		
		
		
	}
	
	@GetMapping("/getAllBillsForCustomer/{mobileNo}")
	public List<BillPayment> getAllBillPayementForCustomer(@PathVariable String mobileNo){
		
		return billService.getTheBillPaymentOfCustomer(mobileNo);
	}
	
	
	
	
}
