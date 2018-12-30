package com.sti.gildaapps.dao.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sti.gildaapps.model.Account;
import com.sti.gildaapps.model.Customer;

public interface AccountRepository extends PagingAndSortingRepository<Account, Integer>{ //integer ? tipe dari primary key nya
	 Account findByAccountNumber(int accountnumber);
	 List<Account> findByCustomer(Customer customer);
	 //find by = select * from account where accountnumber and bla bla bla

}
