package com.IW.model.objects;

import com.IW.interfaces.IBeans;
import com.IW.interfaces.IBeans.IBook;

public class Chapter implements IBeans.IChapter{

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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getnPage() {
		return nPage;
	}
	public void setnPage(int nPage) {
		this.nPage = nPage;
	}
	public IBook getBook() {
		return book;
	}
	public void setBook(IBook book) {
		this.book = book;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	
	
}
