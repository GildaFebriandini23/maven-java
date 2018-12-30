package com.sti.gildaapps.dao.repository;

import com.sti.gildaapps.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {
	Customer findByCustomerNumber(int customernumber);
}
