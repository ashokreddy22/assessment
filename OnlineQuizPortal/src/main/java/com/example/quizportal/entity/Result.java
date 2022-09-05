package com.example.quizportal.entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "result")
public class Result {
	@Id
	private String emailid;
	private int score;
	private String title;
	private int standing_position;
	
	
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getStanding_position() {
		return standing_position;
	}
	public void setStanding_position(int standing_position) {
		this.standing_position = standing_position;
	}
	@Override
	public String toString() {
		return "Result of the participant " + emailid + " of subject "+title+" : score = " + score + ", rank = " + standing_position ;
	}
	public Result(String emailid, int score, String title, int standing_position) {
		super();
		this.emailid = emailid;
		this.score = score;
		this.title = title;
		this.standing_position = standing_position;
	}
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}