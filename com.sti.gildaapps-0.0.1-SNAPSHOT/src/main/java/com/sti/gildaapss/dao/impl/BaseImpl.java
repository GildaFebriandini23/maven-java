package com.sti.gildaapss.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

public abstract class BaseImpl {
	@PersistenceContext
	protected EntityManager em; //dibutuhkan saat mengakses object di repository
	@PersistenceUnit
	protected EntityManagerFactory emf;

}
