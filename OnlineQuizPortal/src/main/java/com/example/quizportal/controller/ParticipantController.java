package com.example.quizportal.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizportal.entity.Participant;
import com.example.quizportal.entity.Quiz;
import com.example.quizportal.entity.Result;
import com.example.quizportal.service.LoginService;
import com.example.quizportal.service.ParticipantService;
import com.example.quizportal.service.QuizService;
import com.example.quizportal.service.ResultService;



//	http://localhost:9191/participants

@RestController
@RequestMapping("participant")
public class ParticipantController {
	
	@Autowired
	LoginService loginService;
	@Autowired
	ParticipantService participantService;
	@Autowired
	QuizService quizService;
	@Autowired
	ResultService resultService;
	
	
//	http://localhost:9191/participants/signup
	@PostMapping(value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String signup(@RequestBody Participant participants) {
		return participantService.addParticipant(participants);
	}
	
//	http://localhost:9191/participants/takeQuiz/1
	@GetMapping(value = "takeQuiz/{id}")
	public String takeQuiz(@PathVariable("id") Quiz quiz) {
		return quizService.findQuiz(quiz);
	}
	
	
//	http://localhost:9191/participants/signIn
	@PostMapping (value = "userSignIn", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String signIn(@RequestBody Participant participant) {
		return participantService.signInUser(participant);
	}
	
	
//	http://localhost:9191/participants/checkAllQuizDetails
	@GetMapping(value = "checkAllQuizDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Quiz> checkAllQuizDetails(){
		return quizService.getAllQuizes();
	}

	
//	http://localhost:9191/participants/checkResult
	@GetMapping(value = "checkResult", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Result> checkResult(){
		return resultService.getAllResult();
	}
	
//	http://localhost:9191/participants/checkResult1
	@PostMapping(value = "checkResult1", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String checkResult1(@RequestBody Result result) {
		return resultService.findByIdUser(result);
	}

}