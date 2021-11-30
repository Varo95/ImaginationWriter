package com.IW.model.objects;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import com.IW.interfaces.IBeans.IPart;
import com.IW.interfaces.IBeans.IChapter;

public class Part implements Serializable, IPart{

	@Serial
	private static final long serialVersionUID = 1L;

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
	@Override
	public long getId() {
		return id;
	}
	@Override
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public int getNPart() {
		return nPart;
	}
	@Override
	public void setNPart(int nPart) {
		this.nPart = nPart;
	}
	@Override
	public List<IChapter> getChapters() {
		return chapters;
	}
	@Override
	public void setChapters(List<IChapter> chapters) {
		this.chapters = chapters;
	}

}
