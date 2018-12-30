package com.sti.gildaapps.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sti.gildaapps.model.Customer;

import java.util.Date;


public class AccountDto {
	
	public AccountDto() {}
	
	private int accountNumber;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="EST")
	private Date openDate;
	private String balance;
	private Customer customer;
	
	public AccountDto(Date openDate, String balance, Customer customer) {
		this.openDate = openDate;
		this.balance = balance;
		this.customer = customer;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	
	
	}
	
	

