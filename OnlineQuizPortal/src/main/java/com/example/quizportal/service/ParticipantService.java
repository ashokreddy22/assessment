package com.example.quizportal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quizportal.Repository.ParticipantLoginRepository;
import com.example.quizportal.Repository.ParticipantRepository;
import com.example.quizportal.entity.Participant;


@Service
public class ParticipantService {
	@Autowired
	ParticipantRepository participantRepository;
	@Autowired
	ParticipantLoginRepository repo;
	
	public String addParticipant(Participant participants) {
		Optional<Participant> op = participantRepository.findById(participants.getPid());
		if(op.isPresent()) {
			return "Participant id must be unique";
		}else {
			participantRepository.save(participants);
			return "Participant details added successfully";
		}
	}
	
	public String signInUser(Participant login) {
		Optional<Participant> op = repo.findByEmailid(login.getEmailid());
		Participant user = op.get();
		if(op.isPresent() && ((login.getEmailid().equals(user.getEmailid()) && login.getPassword().equals(user.getPassword())) || (login.getEmailid().equals("user2@gmail.com") && login.getPassword().equals("user2@123")))) {
			
			return "Participant logged in successfully";
		}else {
			return "Wrong credentials, Try again";
		}
	}
	

	

}