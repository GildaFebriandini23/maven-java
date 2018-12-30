package com.sti.gildaapss.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.sti.gildaapps.dao.CustomerDao;
import com.sti.gildaapps.dao.repository.CustomerRepository;
import com.sti.gildaapps.exception.AppsException;
import com.sti.gildaapps.model.Customer;

public class CustomerDaoImpl extends BaseImpl implements CustomerDao {

	@Autowired
	private CustomerRepository repository;
	
	@Override
	public Customer getById(int id) throws AppsException {
		return repository.findOne(Integer.valueOf(id));
	}

	@Override
	public Customer save(Customer customer) throws AppsException {
		return repository.save(customer);
	}

	@Override
	public void delete(Customer customer) throws AppsException {
		repository.delete(customer);	
	}

	@Override
	public List<Customer> getList() throws AppsException {
		CriteriaBuilder critB = em.getCriteriaBuilder();
		CriteriaQuery<Customer> query = critB.createQuery(Customer.class);
		Root<Customer> root = query.from(Customer.class);
		query.select(root);
		
		TypedQuery<Customer> q = em.createQuery(query);
		
		return q.getResultList();
	}

	
}
