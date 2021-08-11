package com.ratan.service;

import java.util.List;

import com.ratan.model.BillPayment;
import com.ratan.model.BillType;

public interface BillPaymentService {

	public BillPayment payTheBill(String mobileNo,BillType billType,double amount);
	
	public List<BillPayment> getTheBillPaymentOfCustomer(String mobileNo);
	
	
	
}
