package com.IW.model.objects;

import com.IW.interfaces.IBeans;
import com.IW.interfaces.IBeans.IBook;;

public class Character implements IBeans.ICharacter{
	
	protected long id;
	protected String name;
	protected String description;
	protected String photo;
	protected IBook book;
	
	public Character(long id, String name, String description, String photo, IBook book) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.photo = photo;
		this.book = book;
	}
	
	public Character() {}
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public IBook getBook() {
		return book;
	}
	public void setBook(IBook book) {
		this.book = book;
	}
	
	

}
