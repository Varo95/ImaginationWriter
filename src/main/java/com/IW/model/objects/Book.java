package com.IW.model.objects;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import com.IW.interfaces.IBeans.IBook;
import com.IW.interfaces.IBeans.IAuthor;
import com.IW.interfaces.IBeans.ICharacter;
import com.IW.interfaces.IBeans.IPart;
import com.IW.interfaces.IBeans.IScene;

import javax.persistence.*;

@Entity
@Table(name = "Book")
public class Book implements Serializable, IBook {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected long id;
    @Column(name = "title")
    protected String title;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    protected List<Part> parts;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    protected List<Character> characters;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    protected List<Scene> scenes;
    @JoinTable(name = "author_book",
            joinColumns = @JoinColumn(name = "id_book", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_author", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    protected List<Author> editors;
    @Column(name = "cover")
    protected String cover;
    @ManyToOne
    protected Author creator;
    @Transient
    protected int nPages;

    public Book(long id, String title, List<Part> parts, List<Character> characters, List<Scene> scenes, List<Author> editors,
                String cover, Author creator) {
        super();
        this.id = id;
        this.title = title;
        this.parts = parts;
        this.scenes = scenes;
        this.characters = characters;
        this.editors = editors;
        this.cover = cover;
        this.creator = creator;
    }

    public Book() {
        this.id = -1;
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
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public List<IPart> getParts() {
        return (List<IPart>) _getParts();
    }

    private List<? extends IPart> _getParts() {
        return parts;
    }

    @Override
    public void setParts(List<IPart> parts) {
        this._setParts(parts);
    }

    private void _setParts(List<? extends IPart> parts) {
        this.parts = (List<Part>) parts;
    }

    @Override
    public List<ICharacter> getCharacters() {
        return (List<ICharacter>) _getCharacters();
    }

    private List<? extends ICharacter> _getCharacters() {
        return characters;
    }

    @Override
    public void setCharacters(List<ICharacter> characters) {
        this._setCharacters(characters);
    }

    private void _setCharacters(List<? extends ICharacter> characters) {
        this.characters = (List<Character>) characters;
    }

    @Override
    public List<IScene> getScenes() {
        return (List<IScene>) _getScenes();
    }

    private List<? extends IScene> _getScenes() {
        return this.scenes;
    }

    @Override
    public void setScenes(List<IScene> scenes) {
        this._setScenes(scenes);
    }

    private void _setScenes(List<? extends IScene> scenes) {
        this.scenes = (List<Scene>) scenes;
    }

    public List<IAuthor> getEditors() {
        return (List<IAuthor>) _getAuthors();
    }

    private List<? extends IAuthor> _getAuthors() {
        return this.editors;
    }

    public void setEditors(List<IAuthor> authors) {
        this._setAuthors(authors);
    }

    private void _setAuthors(List<? extends IAuthor> authors) {
        this.editors = (List<Author>) authors;
    }

    @Override
    public String getCover() {
        return cover;
    }

    @Override
    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public void setCreator(IAuthor creator) {
        this.creator = (Author) creator;
    }

    @Override
    public IAuthor getCreator() {
        return creator;
    }

    @Override
    @Transient
    public int getNPages() {
        return nPages;
    }

    @Override
    @Transient
    public void setNPages(int nPages) {
        this.nPages = nPages;
    }

}
