package com.example.quizportal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quizportal.Repository.LoginRepository;
import com.example.quizportal.Repository.ParticipantRepository;
import com.example.quizportal.entity.Login;
import com.example.quizportal.entity.Participant;


@Service
public class LoginService {
	@Autowired
	LoginRepository loginRepository;
	@Autowired
	ParticipantRepository participantRepository;
	
	
	public String signIn(Login login) {
		Optional<Login> op = loginRepository.findById(login.getEmailid());
		System.out.println(op);
		Login user = op.get();
		if(op.isPresent() && login.getPassword().equals(user.getPassword())) {
			
			return "Admin logged in successfully";
		}else {
			return "Wrong credentials, Try again";
		}
	}
	
	
	public String updatePassword(Login login) {
		Optional<Login> op = loginRepository.findById(login.getEmailid());
		if(op.isPresent()) {
			Login user = op.get();
			user.setPassword(login.getPassword());
			loginRepository.saveAndFlush(user);
			return "Password updated successfully";
		}else {
			return "Password didn't update ,Get Correct Email";
		}
	}
	
	
	

}	
	
	
	
	
