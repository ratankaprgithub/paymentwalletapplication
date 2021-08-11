package com.ratan.service;

import com.ratan.model.Customer;

public interface UserService {
	
	public Customer validateLogin(String mobileNumber,String password);
	
	public Customer viewCustomerByMobileNo(String mobileNo);
		
	public Customer deleteCustomerByMobileNo(String mobileNo);
}
