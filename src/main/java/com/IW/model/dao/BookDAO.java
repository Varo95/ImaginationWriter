package com.IW.model.dao;

import com.IW.interfaces.IBeans.IAuthor;
import com.IW.interfaces.IBeans.IBook;
import com.IW.interfaces.SQL.IBookDAO;
import com.IW.model.objects.Author;
import com.IW.model.objects.Book;
import com.IW.utils.PersistenceUnit;
import org.hibernate.query.NativeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BookDAO extends Book implements IBookDAO {

    private static final Logger logger = LoggerFactory.getLogger(BookDAO.class);

    public BookDAO(long id) {
        Book b = null;
        EntityManager em = PersistenceUnit.createEM();
        em.getTransaction().begin();
        try {
            b = em.find(Book.class, id);
            if (b != null) {
                this.setId(b.getId());
                this.setTitle(b.getTitle());
                this.setParts(b.getParts());
                this.setScenes(b.getScenes());
                this.setCharacters(b.getCharacters());
                this.setEditors(b.getEditors());
                this.setCover(b.getCover());
                this.setCreator(b.getCreator());
            }
        } catch (NoResultException e) {
            logger.error("Libro con id: " + id + "\nNo encontrado en la base de datos");
        }
        PersistenceUnit.closeEM();
    }

    public BookDAO() {
        super();
    }

    @Override
    public void persist() {
        EntityManager em = PersistenceUnit.createEM();
        em.getTransaction().begin();
        System.out.println("Estoy persistiendo puto");
        Book b = em.merge(this);
        em.persist(b);
        if (getId() == -1) {
            em.flush();
            setId(b.getId());
        }
        em.getTransaction().commit();
        PersistenceUnit.closeEM();
    }

    @Override
    public void remove() {
        EntityManager em = PersistenceUnit.createEM();
        em.getTransaction().begin();
        Book b = em.merge(this);
        em.remove(b);
        em.getTransaction().commit();
        PersistenceUnit.closeEM();
    }

    @Override
    public void addAuthor(IAuthor author) {
        EntityManager em = PersistenceUnit.createEM();
        em.getTransaction().begin();
        if(this.getEditors()==null)
            this.editors = new ArrayList<>();
        this.getEditors().add(author);
        em.getTransaction().commit();
        PersistenceUnit.closeEM();
    }

    @Override
    public void deleteAuthor(IAuthor author) {
        EntityManager em = PersistenceUnit.createEM();
        em.getTransaction().begin();
        this.getEditors().remove(author);
        em.getTransaction().commit();
        PersistenceUnit.closeEM();
    }


    public static List<IBook> getBooksAsEditor(IAuthor editor){
        EntityManager em = PersistenceUnit.createEM();
        em.getTransaction().begin();
        String query = "SELECT author_book.id_book FROM author_book WHERE author_book.id_author=:editor";
        Query q = em.createNativeQuery(query);
        q.setParameter("editor",editor.getId());
        List<IBook> result = new ArrayList<>();
        em.getTransaction().commit();
        for(Object o: q.getResultList()){
            result.add(new BookDAO(((BigInteger) o).longValue()));
        }
        PersistenceUnit.closeEM();
        return result;
    }
}
