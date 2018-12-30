package com.sti.gildaapps.dao;

import java.util.List;

import com.sti.gildaapps.exception.AppsException;
import com.sti.gildaapps.model.Account;
import com.sti.gildaapps.model.Transaction;

public interface TransactionDao {
	Transaction getById(int id) throws AppsException;
	Transaction save(Transaction transaction) throws AppsException;
	void delete(Transaction transaction) throws AppsException;
	
	List<Transaction> getList() throws AppsException;
	List<Transaction> getListByAccount(Account account) throws AppsException;
		

}
