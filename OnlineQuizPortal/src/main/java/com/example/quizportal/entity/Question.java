package com.example.quizportal.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "questions")
public class Question {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int qid;
	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String correct_ans;
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	public String getCorrect_ans() {
		return correct_ans;
	}
	public void setCorrect_ans(String correct_ans) {
		this.correct_ans = correct_ans;
	}
	@Override
	public String toString() {
		return "Questions [qid=" + qid + ", question=" + question + ", option1=" + option1 + ", option2=" + option2
				+ ", option3=" + option3 + ", option4=" + option4 + ", correct_ans=" + correct_ans + "]";
	}
	public Question(int qid, String question, String option1, String option2, String option3, String option4,
			String correct_ans) {
		super();
		this.qid = qid;
		this.question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.correct_ans = correct_ans;
	}
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}