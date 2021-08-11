package com.ratan;

import java.math.BigDecimal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.ratan.model.BankAccount;
import com.ratan.model.Wallet;
import com.ratan.service.BankAccountServiceImpl;

@SpringBootApplication
public class PaymentwalletapplicationApplication {

	public static void main(String[] args) {
		ApplicationContext ctx= SpringApplication.run(PaymentwalletapplicationApplication.class, args);
		
		/*
		 * BankAccountServiceImpl ser=
		 * ctx.getBean("bankAccountServiceImpl",BankAccountServiceImpl.class);
		 * 
		 * 
		 * BankAccount acc=new BankAccount(); acc.setBalance(60000);
		 * acc.setBankName("sbi"); acc.setIfscCode("sbiifsc");
		 * 
		 * Wallet w=new Wallet(); w.setBalance(BigDecimal.valueOf(1000));
		 * 
		 * acc.getWallets().add(w);
		 * 
		 * BankAccount bacc= ser.addAccount(acc);
		 * 
		 * System.out.println(bacc.getAccountNo());
		 * 
		 */
		
		
		
		
	}

}
