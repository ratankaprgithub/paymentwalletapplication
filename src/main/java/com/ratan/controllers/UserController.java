package com.ratan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratan.model.Customer;
import com.ratan.model.LoginBean;
import com.ratan.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	/*
	 * 
	  {
    "mobileNo": "3232324",
    "password": "123"
  
		}
	 * 
	 * 
	 */
	
	@PostMapping("/loginUser")
	public Customer validateUser(@RequestBody LoginBean loginBean) {
		
		return userService.validateLogin(loginBean.getMobileNo(), loginBean.getPassword());
		
	}
	
	@GetMapping("/getCustomer/{mobileNo}")
	public Customer getCustomerByMobileNumber(@PathVariable String mobileNo) {
		
		return userService.viewCustomerByMobileNo(mobileNo);
	}
	
	@DeleteMapping("/deleteCustomer/{mobileNo}")
	public Customer deleteCustomer(@PathVariable String mobileNo) {
		return userService.deleteCustomerByMobileNo(mobileNo);
	}
	
	
	
	
}
