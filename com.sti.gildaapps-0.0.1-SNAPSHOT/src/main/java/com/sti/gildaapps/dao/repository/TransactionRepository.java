package com.sti.gildaapps.dao.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sti.gildaapps.model.Account;
import com.sti.gildaapps.model.Transaction;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Integer>{
	 
	 List<Transaction> findByAccount(Account account);

}
