package com.IW.model.objects;

import com.IW.interfaces.IBeans.IAuthor;
import com.IW.interfaces.IBeans.IBook;

import java.util.List;

public class Author implements IAuthor {
    private long id;
    private List<IBook> books;

    public Author() {

    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public List<IBook> getBooks() {
        return this.books;
    }

    @Override
    public void setBooks(List<IBook> books) {
        this.books = books;
    }

    @Override
    public void addBook(IBook book) {
        this.books.add(book);
    }

    @Override
    public void removeBook(IBook book) {
        this.books.remove(book);
    }
}
