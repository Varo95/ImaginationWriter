package com.IW.model.dao;

import javax.persistence.EntityManager;

import com.IW.interfaces.SQL.ICharacterDAO;
import com.IW.model.objects.Character;
import com.IW.utils.PersistenceUnit;

public class CharacterDAO extends Character implements ICharacterDAO{

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
