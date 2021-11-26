package com.IW.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUnit {
    private static EntityManagerFactory emf;

    /**
     * This method return a singleton of EntityManagerFactory
     *
     * @param name name of the entity
     * @return current EntityManagerFactory
     */
    public static EntityManagerFactory getInstance(String name) {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(name);
        }
        return emf;
    }

    /**
     * This method return a EntityManager
     *
     * @param name name of the entityManagerFactory
     * @return an EntityManager ready to use
     */
    public static EntityManager createEM(String name) {
        return getInstance(name).createEntityManager();
    }
}
