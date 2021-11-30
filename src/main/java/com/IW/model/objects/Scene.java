package com.IW.model.objects;

import com.IW.interfaces.IBeans;

public class Scene implements IBeans.IScene{
	
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
	
	
}
