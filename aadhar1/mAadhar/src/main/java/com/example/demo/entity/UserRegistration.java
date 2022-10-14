package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
@Entity
public class UserRegistration {

	    @Id
		@Column(name="id")
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;
	    @Pattern(regexp="(^$|[0-9]{10})")
	    @Column(unique = true)
		private String mobile;
	    @Pattern(regexp="^(?=.*[0-9]).{6,20}$")
		private String password;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public UserRegistration(@Pattern(regexp = "(^$|[0-9]{10})") String mobile,
				@Pattern(regexp = "^(?=.*[0-9]).{6,20}$") String password) {
			super();
			this.mobile = mobile;
			this.password = password;
		}
		public UserRegistration() {
			super();
		}
	
}
