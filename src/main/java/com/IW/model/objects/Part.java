package com.IW.model.objects;

import java.util.List;

import com.IW.interfaces.IBeans;
import com.IW.interfaces.IBeans.IChapter;

public class Part implements IBeans.IPart{

	protected long id;
	protected int nPart;
	protected List<IChapter> chapters;
	
	public Part(long id, int nPart, List<IChapter> chapters) {
		super();
		this.id = id;
		this.nPart = nPart;
		this.chapters = chapters;
	}
	
	public Part() {}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getnPart() {
		return nPart;
	}
	public void setnPart(int nPart) {
		this.nPart = nPart;
	}
	public List<IChapter> getChapters() {
		return chapters;
	}
	public void setChapters(List<IChapter> chapters) {
		this.chapters = chapters;
	}
	
	
}
