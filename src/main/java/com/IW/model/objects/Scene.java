package com.IW.model.objects;

import com.IW.interfaces.IBeans.IScene;

import java.io.Serial;
import java.io.Serializable;

public class Scene implements Serializable, IScene{

	@Serial
	private static final long serialVersionUID = 1L;
	
	protected long id;
	protected String title;
	protected String description;
	protected String photo;
	
	public Scene(long id, String title, String description, String photo) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.photo = photo;
	}
	public Scene() {}
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

}
