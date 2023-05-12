package com.in.hotel.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.in.hotel.wrapper.UserWrapper;
@CrossOrigin
@RequestMapping(path="/user")
public interface UserController {
	
	
	@PostMapping(path="/signUp")
public ResponseEntity<String> signUp(@RequestBody(required=true)Map<String,String>requestMap);
	
	
	@PostMapping(path="/login")
	public ResponseEntity<String> login(@RequestBody(required=true)Map<String,String> requestMap);
	
	@GetMapping (path="/get")
	public ResponseEntity<List<UserWrapper>>getAllUsers();
	
	@PostMapping(path="/update")
	public ResponseEntity<String>update(@RequestBody(required=true)Map<String,String>requestMap);
	
	@GetMapping(path="/checkToken")

	ResponseEntity<String> checkToken();
	
	@PostMapping(path="/changePassword")
	public ResponseEntity<String> changePassword(@RequestBody(required=true)Map<String,String>requestMap);
	
	@PostMapping(path="/forgotPassword")
	public ResponseEntity<String>forgotPassword(@RequestBody(required=true)Map<String,String>requestMap);
	
	
	
}
