package com.IW.model.objects;

import com.IW.interfaces.IBeans.IPart;
import com.IW.interfaces.IBeans.IChapter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
@Entity
@Table(name = "Chapter")
public class Chapter implements Serializable, IChapter{

	@Serial
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected long id;
	@Column(name = "nPage")
	protected int nPage;
	@ManyToOne(fetch = FetchType.EAGER)
	protected Part part;
	@Column(name = "resume")
	protected String resume;
	@Column(name = "note")
	protected String note;
	@Column(name = "text", columnDefinition = "LONGTEXT")
	protected String text;
	
	public Chapter(long id, int nPage, Part part, String resume, String note, String text) {
		this.id = id;
		this.nPage = nPage;
		this.part = part;
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
	public IPart getPart() {
		return part;
	}
	@Override
	public void setPart(IPart part) {
		this.part = (Part)part;
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
