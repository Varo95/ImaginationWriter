package com.IW.model.objects;

import java.util.List;

import com.IW.interfaces.IBeans;
import com.IW.interfaces.IBeans.IAuthor;
import com.IW.interfaces.IBeans.ICharacter;
import com.IW.interfaces.IBeans.IPart;

public class Book implements IBeans.IBook {

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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<IPart> getParts() {
		return parts;
	}
	public void setParts(List<IPart> parts) {
		this.parts = parts;
	}
	public List<ICharacter> getCharacters() {
		return characters;
	}
	public void setCharacters(List<ICharacter> characters) {
		this.characters = characters;
	}
	public List<IAuthor> getAuthors() {
		return authors;
	}
	public void setAuthors(List<IAuthor> authors) {
		this.authors = authors;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public int getnPages() {
		return nPages;
	}
	public void setnPages(int nPages) {
		this.nPages = nPages;
	}

	
	
	
	
}
