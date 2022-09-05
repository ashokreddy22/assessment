package com.example.quizportal.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "participants")
public class Participant {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int pid;
	private String fname;
	private String lname;
	private int age;
	private String emailid;
	private String password;
	

	public Participant(int pid, String fname, String lname, int age, String emailid, String password) {
		super();
		this.pid = pid;
		this.fname = fname;
		this.lname = lname;
		this.age = age;
		this.emailid = emailid;
		this.password = password;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}



	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	@Override
	public String toString() {
		return "Participant [pid=" + pid + ", fname=" + fname + ", lname=" + lname + ", age=" + age + ", emailid="
				+ emailid + ", password=" + password + "]";
	}
	public Participant() {
		super();
	}
}