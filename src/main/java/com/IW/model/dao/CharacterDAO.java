package com.IW.model.dao;

import com.IW.interfaces.SQL.ICharacterDAO;
import com.IW.model.objects.Character;
import com.IW.utils.PersistenceUnit;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class CharacterDAO extends Character implements ICharacterDAO {
    @Override
    public void persist() {
        EntityManager em = PersistenceUnit.createEM();
        em.getTransaction().begin();
        Character c = em.merge(this);
        em.persist(c);
        if (getId() == -1) {
            em.flush();
            setId(c.getId());
        }
        em.getTransaction().commit();
        PersistenceUnit.closeEM();
    }

    @Override
    public void remove() {
        EntityManager em = PersistenceUnit.createEM();
        em.getTransaction().begin();
        em.remove(this);
        em.getTransaction().commit();
        PersistenceUnit.closeEM();
    }
}
