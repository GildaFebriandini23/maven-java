package com.sti.gildaapss.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.sti.gildaapps.dao.AccountDao;
import com.sti.gildaapps.dao.repository.AccountRepository;
import com.sti.gildaapps.exception.AppsException;
import com.sti.gildaapps.model.Account;
import com.sti.gildaapps.model.Customer;

public class AccountDaoImpl extends BaseImpl implements AccountDao {
	
	@Autowired
	private AccountRepository repository; // kenapa private ? karna object repository hanya dipakai di class ini aja

	@Override
	public Account getById(int id) throws AppsException {
		return repository.findOne(Integer.valueOf(id));
	}

	@Override
	public Account save(Account account) throws AppsException {
		return repository.save(account);
	}

	@Override
	public void delete(Account account) throws AppsException {
		repository.delete(account);	
		
	}

	@Override
	public List<Account> getList() throws AppsException {
		CriteriaBuilder critB = em.getCriteriaBuilder();
		CriteriaQuery<Account> query = critB.createQuery(Account.class);
		Root<Account> root = query.from(Account.class);
		query.select(root);
		
		TypedQuery<Account> q = em.createQuery(query);
		
		return q.getResultList();
	}
	
	@Override
	public List<Account> getListByCustomer(Customer customer) throws AppsException {
		return repository.findByCustomer(customer);
	}

	
	

}
