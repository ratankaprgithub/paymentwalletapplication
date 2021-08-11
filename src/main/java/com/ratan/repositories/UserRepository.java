package com.ratan.repositories;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ratan.model.Customer;
import com.ratan.model.Wallet;

@Repository
public interface UserRepository extends JpaRepository<Customer,Integer>{

	@Query("from Customer where mobileNo=?1 AND password=?2")
	public Customer validateLogin(String mobileNumber,String password);
	
	
	public Customer findByMobileNo(String mobileNo);
	

	
}
