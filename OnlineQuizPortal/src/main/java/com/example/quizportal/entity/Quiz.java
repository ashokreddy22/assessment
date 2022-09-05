package com.example.quizportal.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Quiz {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int qzid;
	private String title;
	private int qid;
	public int getQzid() {
		return qzid;
	}
	public void setQzid(int qzid) {
		this.qzid = qzid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	@Override
	public String toString() {
		return "Quizes [qzid=" + qzid + ", title=" + title + ", qid=" + qid + "]";
	}
	public Quiz(int qzid, String title, int qid) {
		super();
		this.qzid = qzid;
		this.title = title;
		this.qid = qid;
	}
	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}