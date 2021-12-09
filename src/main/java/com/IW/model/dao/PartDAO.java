package com.IW.model.dao;

import com.IW.interfaces.SQL.IPartDAO;
import com.IW.model.objects.Part;
import com.IW.utils.PersistenceUnit;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class PartDAO extends Part implements IPartDAO {
    @Override
    public void persist() {
        EntityManager em = PersistenceUnit.createEM();
        em.getTransaction().begin();
        Part p = em.merge(this);
        em.persist(p);
        if (getId() == -1) {
            em.flush();
            setId(p.getId());
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
