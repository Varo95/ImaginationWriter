package com.IW.model.objects;

import com.IW.interfaces.IBeans;
import com.IW.interfaces.IBeans.IAuthor;
import com.IW.interfaces.IBeans.IBook;

import java.util.List;

public class Author implements IBeans.IAuthor{
	
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
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public List<IBook> getBooks() {
		return books;
	}
	public void setBooks(List<IBook> books) {
		this.books = books;
	}
	
	
}
