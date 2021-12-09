package com.IW.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class PersistenceUnit {

    private static final Logger logger = LoggerFactory.getLogger(PersistenceUnit.class);
    private static EntityManagerFactory emf = null;
    private static EntityManager manager = null;
    private static String type = "";

    public static EntityManager createEM() {
        if (emf == null)
            getConnection();
        if (manager == null)
            manager = emf.createEntityManager();
        return manager;
    }

    private static void getConnection() {
        if (type.equals("H2")) {
            try {
                emf = Persistence.createEntityManagerFactory("ApplicationH2");
            } catch (PersistenceException e) {
                logger.error("Sin nombre de proveedor de persistencia para EntityManager con H2");
            }
        } else if (type.equals("MariaDB")) {
            try {
                emf = Persistence.createEntityManagerFactory("ApplicationMariaDB");
            } catch (PersistenceException e) {
                logger.error("Sin nombre de proveedor de persistencia para EntityManager con MariaDB");
            }
        }
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type_) {
        if(type.equals("MariaDB") || type.equals("H2"))
            type = type_;
        else
            type = "H2";
    }

    private static void closeEntityManagerFactory() {
        if (emf != null) {
            emf.close();
            emf = null;
        }
    }

    public static void closeEM() {
        if (manager != null) {
            manager.close();
            manager = null;
        }
    }

    public static void closeAllConnections() {
        closeEM();
        closeEntityManagerFactory();
    }

    public static void changeConnection() {
        closeEntityManagerFactory();
        getConnection();
    }

}
