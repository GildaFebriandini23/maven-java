package com.sti.gildaapps.model.dto;

import com.sti.gildaapps.model.Account;

public class TransactionDto {
	private int id;
	private String type;
	private String amount;
	private String amountSign;
	private Account account;
	
	public TransactionDto() {}

	public TransactionDto(String type, String amount, String amountSign, Account account) {
		super();
		this.type = type;
		this.amount = amount;
		this.amountSign = amountSign;
		this.account = account;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAmountSign() {
		return amountSign;
	}

	public void setAmountSign(String amountSign) {
		this.amountSign = amountSign;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	

	
}
