package com.IW.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class PersistenceUnit {

    private static EntityManagerFactory emfMDB;
    private static EntityManagerFactory emfH2;
    private static EntityManagerFactory current;
    private static boolean isH2;

    private static final Logger logger = LoggerFactory.getLogger(PersistenceUnit.class);

    /**
     * This is used to get the current instance of EntityManager, if MariaDB or H2
     * @param connectionType type connection, MariaDB or H2
     */
    public static void setInstance(String connectionType){
        if(connectionType.equals("MariaDB")){
            current = getInstanceMariaDB();
            isH2 = false;
        }else{
            current = getInstanceH2();
            isH2 = true;
        }
    }

    public EntityManagerFactory getInstance(){
        return current;
    }

    private static EntityManagerFactory getInstanceMariaDB() {
        if (emfMDB == null) {
            try {
                emfMDB = Persistence.createEntityManagerFactory("ApplicationMariaDB");
            }catch (PersistenceException e){
                logger.error("Sin nombre de proveedor de persistencia para EntityManager ApplicationMariaDB");
            }
        }
        return emfMDB;
    }

    private static EntityManagerFactory getInstanceH2(){
        if(emfH2 == null){
            try {
                emfH2 = Persistence.createEntityManagerFactory("ApplicationH2");
            }catch (PersistenceException e){
                logger.error("Sin nombre de proveedor de persistencia para EntityManager ApplicationH2");
            }
        }
        return emfH2;
    }

    /**
     * This method return a EntityManager
     *
     * @return an EntityManager ready to use
     */
    public static EntityManager createEM() {
        if(current!=null)
            return current.createEntityManager();
        else
            return getInstanceH2().createEntityManager();
    }

    public static boolean isH2(){
        return isH2;
    }

}
