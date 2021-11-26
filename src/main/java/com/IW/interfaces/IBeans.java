package com.IW.interfaces;

import java.util.List;

public interface IBeans {
    interface IAuthor{
        long getId();
        void setId(long id);
        List<IBook> getBooks();
        void setBooks(List<IBook> books);
        void addBook(IBook book);
        void removeBook(IBook book);
    }

    interface IBook{
        long getId();
        void setId(long id);
    }
}
