package com.in.hotel.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.processing.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@javax.persistence.NamedQuery(name="User.findByEmailId",query="select u from User u where u.email=:email")
@NamedQuery(name="User.getAllUser",query="select new com.in.hotel.wrapper.UserWrapper(u.id,u.name,u.email,u.contactNumber,u.status) from User u where u.role='user'")
@NamedQuery(name="User.updateStatus",query="Update User u set u.status=:status where u.id=:id")
@NamedQuery(name="User.getAllAdmin",query="select u.email from User u where u.role='admin'")
@Entity
@Data
@DynamicInsert
@DynamicUpdate
@javax.persistence.Table(name="user")


public class User implements Serializable {
 
 
	private static final long serialVersionUID = 1L;
@javax.persistence.Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="id")
 private Integer id;
 @Column(name="name")
 private String name;
 @Column(name="contactNumber")
 private String contactNumber;
 @Column(name="email")
 private String email;
 @Column(name="password")
 private String password;
 @Column(name="status")
 private String status;
 @Column(name="role")
 private String role;
 
 
 
 


 
 
}
