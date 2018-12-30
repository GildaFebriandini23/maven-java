package com.sti.gildaapps.dao;

import com.sti.gildaapps.exception.AppsException;
import com.sti.gildaapps.model.Account;
import com.sti.gildaapps.model.Customer;

import java.util.List;

public interface AccountDao {
	Account getById(int id) throws AppsException;
	Account save(Account account) throws AppsException;
	void delete(Account account) throws AppsException;
	
	List<Account> getList() throws AppsException;
	List<Account> getListByCustomer(Customer customer) throws AppsException;
}
