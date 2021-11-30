package com.IW.interfaces;

import java.util.List;

public interface IBeans {
    interface IAuthor{
        long getId();
        void setId(long id);
        String getPassword();
        void setPassword(String password);
        List<IBook> getBooks();
        void setBooks(List<IBook> books);
        String getName();
        void setName(String name);
        String getPhoto();
        void setPhoto(String photo);
    }

    interface IBook{
        long getId();
        void setId(long id);
        String getTitle();
        void setTitle(String title);
        List<IPart> getParts();
        void setParts(List<IPart> parts);
        List<ICharacter> getCharacters();
        void setCharacters(List<ICharacter> characters);
        List<IAuthor> getAuthors();
        void setAuthors(List<IAuthor> authors);
        String getCover();
        void setCover(String cover);
        int getNPages();
        void setNPages(int nPages);
    }

    interface IChapter{
        long getId();
        void setId(long id);
        int getNPage();
        void setNPage(int nPage);
        IBook getBook();
        void setBook(IBook book);
        String getResume();
        void setResume(String resume);
        String getNote();
        void setNote(String note);
        void setText(String text);
        String getText();
    }

    interface ICharacter{
        long getId();
        void setId(long id);
        String getName();
        void setName(String name);
        String getDescription();
        void setDescription(String description);
        String getPhoto();
        void setPhoto(String photo);
        IBook getBook();
        void setBook(IBook book);
    }

    interface IScene{
        long getId();
        void setId(long id);
        String getTitle();
        void setTitle(String title);
        String getDescription();
        void setDescription(String description);
        String getPhoto();
        void setPhoto(String photo);
    }

    interface IPart{
        long getId();
        void setId(long id);
        int getNPart();
        void setNPart(int nPart);
        List<IChapter> getChapters();
        void setChapters(List<IChapter> chapters);
    }
}
