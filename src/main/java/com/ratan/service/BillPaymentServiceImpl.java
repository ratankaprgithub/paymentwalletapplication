package com.ratan.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ratan.exception.CustomerNotFoundException;
import com.ratan.exception.InsufficientAmountException;
import com.ratan.model.BillPayment;
import com.ratan.model.BillType;
import com.ratan.model.Customer;
import com.ratan.model.TransactionBean;
import com.ratan.model.Wallet;
import com.ratan.repositories.BillRepository;
import com.ratan.repositories.TransactionRepo;
import com.ratan.repositories.UserRepository;

@Service
public class BillPaymentServiceImpl implements BillPaymentService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BillRepository billRepo;
	
	@Autowired
	private TransactionRepo transactionRepo;
	
	@Transactional
	@Override
	public BillPayment payTheBill(String mobileNo, BillType billType,double amount) {
		
		Customer customer = userRepo.findByMobileNo(mobileNo);		
		
		if(customer == null)
			throw new CustomerNotFoundException("Customer not found with Mobile number :"+mobileNo);
		
		Wallet w= customer.getWallet();
		
		if(w.getBalance().compareTo(BigDecimal.valueOf(amount)) >= 0)
		{
		
			w.setBalance(w.getBalance().subtract(BigDecimal.valueOf(amount)));
			
			customer.setWallet(w);
			
			
		BillPayment bill=new BillPayment();
		bill.setBillType(billType);
		bill.setAmount(amount);
		bill.setPaymentDate(LocalDate.now());
		bill.setMobileNo(mobileNo);
			
		userRepo.save(customer);
		
		TransactionBean tr=new TransactionBean();
		tr.setTransactionType("Bill Payment for "+billType.toString());
		tr.setAmount(amount);
		tr.setMobileNo(mobileNo);
		tr.setTransactionDate(LocalDate.now());
		tr.setDescription("Bill Payment dome for "+billType.toString());
		
		transactionRepo.save(tr);
		
		
		
 	return billRepo.save(bill);
		}
		else
			throw new InsufficientAmountException("Insufficient amount in the wallet to pay the bill");
			
			
	}


	
	@Override
	public List<BillPayment> getTheBillPaymentOfCustomer(String mobileNo) {
		
		return billRepo.findByMobileNo(mobileNo);
		
	}



}
