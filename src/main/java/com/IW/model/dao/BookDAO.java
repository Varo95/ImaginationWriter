package com.IW.model.dao;

import javax.persistence.EntityManager;

import com.IW.interfaces.SQL.IBookDAO;
import com.IW.model.objects.Book;
import com.IW.utils.PersistenceUnit;

public class BookDAO extends Book implements IBookDAO{

	@Override
	public void persist() {
		// TODO Auto-generated method stub
		EntityManager em = PersistenceUnit.createEM();
        em.getTransaction().begin();
        em.persist(this);
        em.getTransaction().commit();
		
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		EntityManager em = PersistenceUnit.createEM();
        em.getTransaction().begin();
        em.remove(this);
        em.getTransaction().commit();
	}

}
