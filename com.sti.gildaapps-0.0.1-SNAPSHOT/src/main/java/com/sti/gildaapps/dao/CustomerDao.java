package com.sti.gildaapps.dao;

import com.sti.gildaapps.exception.AppsException;
import com.sti.gildaapps.model.Customer;
import java.util.List;

public interface CustomerDao {
	Customer getById(int id) throws AppsException;
	Customer save(Customer customer) throws AppsException;
	void delete(Customer customer) throws AppsException;
	List<Customer> getList() throws AppsException;

}
