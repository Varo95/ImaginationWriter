package com.IW.model.objects;

import com.IW.interfaces.IBeans.IAuthor;
import com.IW.interfaces.IBeans.IBook;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class Author implements Serializable, IAuthor{

	@Serial
	private static final long serialVersionUID = 1L;
	
	protected long id;
	protected String name;
	protected String password;
	protected String photo;
	protected List<IBook> books;
	
	public Author(long id, String name, String password, String photo, List<IBook> books) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.photo = photo;
		this.books = books;
	}
	
	public Author() {}
	@Override
	public long getId() {
		return id;
	}
	@Override
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String getPhoto() {
		return photo;
	}
	@Override
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@Override
	public List<IBook> getBooks() {
		return books;
	}
	@Override
	public void setBooks(List<IBook> books) {
		this.books = books;
	}
	
	
}
