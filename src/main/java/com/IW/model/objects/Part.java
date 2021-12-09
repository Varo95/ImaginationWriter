package com.IW.model.objects;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.IW.interfaces.IBeans.IBook;
import com.IW.interfaces.IBeans.IPart;
import com.IW.interfaces.IBeans.IChapter;

import javax.persistence.*;

@Entity
public class Part implements Serializable, IPart {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected long id;
    @Column(name = "nPart")
    protected int nPart;
    @OneToMany(mappedBy = "part", fetch = FetchType.LAZY)
    protected List<Chapter> chapters;
    @ManyToOne
    protected Book book;

    public Part(long id, int nPart, List<Chapter> chapters, Book book) {
        super();
        this.id = id;
        this.nPart = nPart;
        this.chapters = chapters;
        this.book = book;
    }

    public Part() {
        this.id = -1;
        this.chapters = new ArrayList<>();
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int getNPart() {
        return nPart;
    }

    @Override
    public void setNPart(int nPart) {
        this.nPart = nPart;
    }

    @Override
    public List<IChapter> getChapters() {
        return (List<IChapter>) _getChapters();
    }

    private List<? extends IChapter> _getChapters() {
        return this.chapters;
    }

    @Override
    public void setChapters(List<IChapter> chapters) {
        this._setChapters(chapters);
    }

    private void _setChapters(List<? extends IChapter> chapters) {
        this.chapters = (List<Chapter>) chapters;
    }

    @Override
    public IBook getBook() {
        return book;
    }

    @Override
    public void setBook(IBook book) {
        this.book = (Book) book;
    }

    @Override
    public String toCombobox(){
        return "Parte: "+this.nPart + "."+this.id;
    }
}
