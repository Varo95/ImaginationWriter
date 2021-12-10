package com.IW.utils;

import com.IW.model.objects.Character;
import com.IW.model.objects.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenceUnit {
    //TODO cambiar métodos para persistir siempre en local
    //TODO agregar métodos para; descargar de MariaDB a H2, subir de H2 a MariaDB
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

    public static void copyH2toMariaDB(){
        if(emf!=null){
            EntityManagerFactory temp = Persistence.createEntityManagerFactory("ApplicationMariaDB");
            EntityManager t = temp.createEntityManager();
            t.getTransaction().begin();
            for(Author a: getAllAuthors()){
                a = t.merge(a);
                t.persist(a);
            }
            for(Book b: getAllBooks()){
                b = t.merge(b);
                t.persist(b);
            }
            for(Chapter c : getAllChapters()){
                c = t.merge(c);
                t.persist(c);
            }
            for(Character c: getAllCharacters()){
                c = t.merge(c);
                t.persist(c);
            }
            for(Part p: getAllParts()){
                p = t.merge(p);
                t.persist(p);
            }
            for(Scene s: getAllScenes()){
                s = t.merge(s);
                t.persist(s);
            }
            t.getTransaction().commit();
            temp.close();
        }
    }

    public static void copyMariaDBToH2(){
        if(emf!=null){
            //TODO logic here
        }
    }

    private static List<Author> getAllAuthors(){
        EntityManager em = createEM();
        em.getTransaction().begin();
        TypedQuery<Author> q = em.createNamedQuery("listAll", Author.class);
        List<Author> result = new ArrayList<>(q.getResultList());
        em.getTransaction().commit();
        closeEM();
        return result;
    }
    private static List<Book> getAllBooks(){
        EntityManager em = createEM();
        em.getTransaction().begin();
        TypedQuery<Book> q = em.createQuery("SELECT b FROM Book b", Book.class);
        List<Book> result = new ArrayList<>(q.getResultList());
        em.getTransaction().commit();
        closeEM();
        return result;
    }
    private static List<Chapter> getAllChapters(){
        EntityManager em = createEM();
        em.getTransaction().begin();
        TypedQuery<Chapter> q = em.createQuery("SELECT c FROM Chapter c", Chapter.class);
        List<Chapter> result = new ArrayList<>(q.getResultList());
        em.getTransaction().commit();
        closeEM();
        return result;
    }
    private static List<Character> getAllCharacters(){
        EntityManager em = createEM();
        em.getTransaction().begin();
        TypedQuery<Character> q = em.createQuery("SELECT c FROM Character c", Character.class);
        List<Character> result = new ArrayList<>(q.getResultList());
        em.getTransaction().commit();
        closeEM();
        return result;
    }
    private static List<Part> getAllParts(){
        EntityManager em = createEM();
        em.getTransaction().begin();
        TypedQuery<Part> q = em.createQuery("SELECT p FROM Part p", Part.class);
        List<Part> result = new ArrayList<>(q.getResultList());
        em.getTransaction().commit();
        closeEM();
        return result;
    }
    private static List<Scene> getAllScenes(){
        EntityManager em = createEM();
        em.getTransaction().begin();
        TypedQuery<Scene> q = em.createQuery("SELECT s FROM Scene s", Scene.class);
        List<Scene> result = new ArrayList<>(q.getResultList());
        em.getTransaction().commit();
        closeEM();
        return result;
    }

}
