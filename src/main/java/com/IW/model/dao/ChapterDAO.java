package com.IW.model.dao;

import com.IW.model.objects.Chapter;
import com.IW.utils.PersistenceUnit;

import javax.persistence.EntityManager;

import com.IW.interfaces.SQL.IChapterDAO;

public class ChapterDAO extends Chapter implements IChapterDAO{

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
