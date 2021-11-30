package com.IW.model.objects;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import com.IW.interfaces.IBeans.IBook;
import com.IW.interfaces.IBeans.IAuthor;
import com.IW.interfaces.IBeans.ICharacter;
import com.IW.interfaces.IBeans.IPart;

public class Book implements Serializable, IBook {

	@Serial
	private static final long serialVersionUID = 1L;

	protected long id;
	protected String title;
	protected List<IPart> parts;
	protected List<ICharacter> characters;
	protected List<IAuthor> authors;
	protected String cover;
	protected int nPages;
	
	public Book(long id, String title, List<IPart> parts, List<ICharacter> characters, List<IAuthor> authors,
			String cover, int nPages) {
		super();
		this.id = id;
		this.title = title;
		this.parts = parts;
		this.characters = characters;
		this.authors = authors;
		this.cover = cover;
		this.nPages = nPages;
	}
	public Book() {}
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
		return parts;
	}
	@Override
	public void setParts(List<IPart> parts) {
		this.parts = parts;
	}
	@Override
	public List<ICharacter> getCharacters() {
		return characters;
	}
	@Override
	public void setCharacters(List<ICharacter> characters) {
		this.characters = characters;
	}
	@Override
	public List<IAuthor> getAuthors() {
		return authors;
	}
	@Override
	public void setAuthors(List<IAuthor> authors) {
		this.authors = authors;
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
	public int getNPages() {
		return nPages;
	}
	@Override
	public void setNPages(int nPages) {
		this.nPages = nPages;
	}

	
	
	
	
}
