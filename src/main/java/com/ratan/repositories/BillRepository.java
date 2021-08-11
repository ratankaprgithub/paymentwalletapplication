package com.ratan.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ratan.model.BillPayment;

public interface BillRepository extends JpaRepository<BillPayment,Integer>{

	public List<BillPayment> findByMobileNo(String mobileNo);
}
