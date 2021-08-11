package com.ratan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratan.exception.CustomerNotFoundException;
import com.ratan.model.Customer;
import com.ratan.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	
	@Override
	public Customer validateLogin(String mobileNumber, String password) {
		
		
		Customer customer= userRepo.validateLogin(mobileNumber, password);
		
		if(customer != null)
			return customer;
		else
			throw new CustomerNotFoundException("Invalid Mobile number or password");
		
		
		
	}


	@Override
	public Customer viewCustomerByMobileNo(String mobileNo) {
		
		Customer customer= userRepo.findByMobileNo(mobileNo);
		
		if(customer == null)
			throw new CustomerNotFoundException("Customer not found with given Mobile Number");
		else
			return customer;
		
		
	}


	@Override
	public Customer deleteCustomerByMobileNo(String mobileNo) {
		
 		Customer customer = userRepo.findByMobileNo(mobileNo);
		
 		if(customer == null)
 			throw new CustomerNotFoundException("Customer Not found with mobile No :"+mobileNo);
		
 		userRepo.delete(customer);
 		
 		
 		return customer;
 		
 		
	}

}
