package com.sti.gildaapss.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;

import com.sti.gildaapps.dao.TransactionDao;
import com.sti.gildaapps.dao.repository.TransactionRepository;
import com.sti.gildaapps.exception.AppsException;
import com.sti.gildaapps.model.Account;
import com.sti.gildaapps.model.Transaction;

public class TransactionDaoImpl extends BaseImpl implements TransactionDao {
	
	@Autowired
	private TransactionRepository repository;

	@Override
	public Transaction getById(int id) throws AppsException {
		return repository.findOne(Integer.valueOf(id));
	}

	@Override
	public Transaction save(Transaction transaction) throws AppsException {
		return repository.save(transaction);
	}

	@Override
	public void delete(Transaction transaction) throws AppsException {
		repository.delete(transaction);
		
	}

	@Override
	public List<Transaction> getList() throws AppsException {
		CriteriaBuilder critB = em.getCriteriaBuilder();
		CriteriaQuery<Transaction> query = critB.createQuery(Transaction.class);
		Root<Transaction> root = query.from(Transaction.class);
		query.select(root);
		
		TypedQuery<Transaction> q = em.createQuery(query);
		
		return q.getResultList();
	}
	
	@Override
	public List<Transaction> getListByAccount(Account account) throws AppsException {
		return repository.findByAccount(account);
	}

}
