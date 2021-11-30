package com.IW.model.objects;

import com.IW.interfaces.IBeans;
import com.IW.interfaces.IBeans.IAuthor;
import com.IW.interfaces.IBeans.IBook;

import java.util.List;
//TODO poner implements y crear el bean
public class Author {
    private long id;
    private List<IBook> books;

    public Author() {

    }


    public long getId() {
        return this.id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public List<IBook> getBooks() {
        return this.books;
    }


    public void setBooks(List<IBook> books) {
        this.books = books;
    }

}
