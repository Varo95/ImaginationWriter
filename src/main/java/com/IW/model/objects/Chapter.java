package com.IW.model.objects;

import com.IW.interfaces.IBeans.IChapter;
import com.IW.interfaces.IBeans.IBook;

import java.io.Serial;
import java.io.Serializable;

public class Chapter implements Serializable, IChapter{

	@Serial
	private static final long serialVersionUID = 1L;

	protected long id;
	protected int nPage;
	protected IBook book;
	protected String resume;
	protected String note;
	protected String text;
	
	public Chapter(long id, int nPage, IBook book, String resume, String note, String text) {
		this.id = id;
		this.nPage = nPage;
		this.book = book;
		this.resume = resume;
		this.note = note;
		this.text = text;
	}
	public Chapter() {}
	@Override
	public long getId() {
		return id;
	}
	@Override
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public int getNPage() {
		return nPage;
	}
	@Override
	public void setNPage(int nPage) {
		this.nPage = nPage;
	}
	@Override
	public IBook getBook() {
		return book;
	}
	@Override
	public void setBook(IBook book) {
		this.book = book;
	}
	@Override
	public String getResume() {
		return resume;
	}
	@Override
	public void setResume(String resume) {
		this.resume = resume;
	}
	@Override
	public String getNote() {
		return note;
	}
	@Override
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String getText() {
		return text;
	}
	@Override
	public void setText(String text) {
		this.text = text;
	}

	
	
}
