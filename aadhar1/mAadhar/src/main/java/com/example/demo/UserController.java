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
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable int id,@RequestBody User newUser){
		User user= service.updateUser(id, newUser);
		
		if (user!=null)
			return new ResponseEntity<Object>(user,HttpStatus.OK);
		else
			return new ResponseEntity<Object>("No User Available to Update",HttpStatus.NOT_FOUND);
	}
	
	
}
