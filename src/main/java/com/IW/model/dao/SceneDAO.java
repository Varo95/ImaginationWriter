package com.IW.model.dao;

import com.IW.interfaces.SQL.ISceneDAO;
import com.IW.model.objects.Scene;
import com.IW.utils.PersistenceUnit;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class SceneDAO extends Scene implements ISceneDAO {

    @Override
    public void persist() {
        EntityManager em = PersistenceUnit.createEM();
        em.getTransaction().begin();
        Scene s = em.merge(this);
        em.persist(s);
        if (getId() == -1) {
            em.flush();
            setId(s.getId());
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
