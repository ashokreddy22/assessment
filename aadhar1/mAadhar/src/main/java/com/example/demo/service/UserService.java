package com.example.demo.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRegistration;
import com.example.demo.repo.UserRegisrepo;
import com.example.demo.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo repo;
	@Autowired
	private UserRegisrepo repos;
	public User addUser(User u) {
		return repo.save(u);
	}
	public UserRegistration RegisterUser(UserRegistration u) {
		return repos.save(u);
	}
	public List<User> getAllUser(){
		return repo.findAll();
	}
	public User getUserById(int id) {
		if(repo.findById(id).isPresent()) {
			return repo.findById(id).get();
		}
		else {
			return null;
		}
		
	}
	public User updateUser(int id, User newUser) {
		if(repo.findById(id).isPresent()) {
			User olduser= repo.findById(id).get();
			olduser.setName(newUser.getName());
			olduser.setMobile(newUser.getMobile());
			olduser.setAddress(newUser.getAddress());
			olduser.setDob(newUser.getDob());
			olduser.setGender(newUser.getGender());
			olduser.setAction(newUser.getAction());
			return repo.save(olduser);
		}
		else {
			return null;
		}
	}
	
	
	public boolean deleteUser(int id) {
		if(repo.findById(id).isPresent()) {
			repo.deleteById(id);
			return true;
		}
		
		else {
			return false;
		}
	}
	public int adminApprove(int id) {
		return repo.approve(id);
		
	}
	@PersistenceContext
	EntityManager em  ;
	public List<User> getAllUser1()
	{
		Query query = em.createQuery("select u.id,u.name,u.mobile,u.address,u.dob,u.dor, u.gender from User u where u.action=1");
		return query.getResultList();
	}
	public List<UserRegistration> getAllUser2() {
		// TODO Auto-generated method stub
		return repos.findAll();
	}
	
}
