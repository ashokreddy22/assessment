package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRegistration;
import com.example.demo.service.UserService;
@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private UserService service;
	
	@GetMapping("/")
	public List<User> getAllUser(){
		return service.getAllUser();
	}
	/*@GetMapping("/{id}")
	public List<User> getUserById(@PathVariable String id){
		  List<User> user= service.getAllUser1();
		  
		  if(user!=null)
			  return  user;
		  else
			  return null;
	}*/
	@GetMapping("/{id}")
    public ResponseEntity<User> getUserById(){
    	User user=service.getUserById(4);
    	if(user!=null)
    		return new ResponseEntity<User>(user,HttpStatus.FOUND);
    	else
    		return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
    }
	@GetMapping("/register")
	public List<UserRegistration> getAllUser1(){
		return service.getAllUser2();
	}
	@PostMapping("/register")
	public ResponseEntity<UserRegistration> registerUser(@RequestBody UserRegistration u){
		
		UserRegistration user= service.RegisterUser(u);
		
		if(user!=null)
			return new ResponseEntity<UserRegistration>(user,HttpStatus.CREATED);
		else
			return new ResponseEntity<UserRegistration>(user,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	@PostMapping("/")
	public ResponseEntity<User> adduser(@RequestBody User u){
		
		User user= service.addUser(u);
		
		if(user!=null)
			return new ResponseEntity<User>(user,HttpStatus.CREATED);
		else
			return new ResponseEntity<User>(user,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable int id){
		int n= service.adminApprove(id);
		
		if (n>0)
			return new ResponseEntity<Object>(HttpStatus.OK);
		else
			return new ResponseEntity<Object>("No User Available to Update",HttpStatus.NOT_FOUND);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String>deleteUser(@PathVariable int id){
		boolean result = service.deleteUser(id);
		if(result) 
			return new ResponseEntity<String>("Object Deleted",HttpStatus.OK);
		else
			return new ResponseEntity<String>("NO user Found", HttpStatus.NOT_FOUND);
		
	}
}
