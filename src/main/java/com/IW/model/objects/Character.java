package com.IW.model.objects;

import com.IW.interfaces.IBeans.ICharacter;
import com.IW.interfaces.IBeans.IBook;
import java.io.Serial;
import java.io.Serializable;

public class Character implements Serializable, ICharacter{

	@Serial
	private static final long serialVersionUID = 1L;
	
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
	public String getDescription() {
		return description;
	}
	@Override
	public void setDescription(String description) {
		this.description = description;
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
	public IBook getBook() {
		return book;
	}
	@Override
	public void setBook(IBook book) {
		this.book = book;
	}

}
