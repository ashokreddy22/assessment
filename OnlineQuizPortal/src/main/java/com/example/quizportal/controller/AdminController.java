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

import com.example.quizportal.entity.Login;
import com.example.quizportal.entity.Question;
import com.example.quizportal.entity.Quiz;
import com.example.quizportal.entity.Result;
import com.example.quizportal.service.LoginService;
import com.example.quizportal.service.QuestionService;
import com.example.quizportal.service.QuizService;
import com.example.quizportal.service.ResultService;

//	http://localhost:9191/admin

@RestController
@RequestMapping("admin")
public class AdminController {
	@Autowired
	LoginService loginService;
	@Autowired
	QuestionService questionsService;
	@Autowired
	QuizService quizesService;
	@Autowired
	ResultService resultService;
	
//	http://localhost:9191/admin/print
	@GetMapping (value = "print")
	public String print() {
		return "Welcome to the Online Quiz Portal --> ADMIN Controller";
	}
	
	
//	http://localhost:9191/admin/signIn
	@PostMapping (value = "signIn", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String signIn(@RequestBody Login login) {
		return loginService.signIn(login);
	}
	
	
//	http://localhost:9191/admin/changePassword
	@PostMapping (value = "changePassword", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String changePassword(@RequestBody Login login) {
		return loginService.updatePassword(login);
	}
	
//	http://localhost:9191/admin/addQuestion
	@PostMapping (value = "addQuestion", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addQuestion(@RequestBody Question ques) {
		return questionsService.storeQuestion(ques);
	}
	
	
//	http://localhost:9191/admin/findQuestionById/2
	@GetMapping (value = "findQuestionById/{qid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Question findQuestionById(@PathVariable("qid") int qid) {
		return questionsService.findQuestion(qid);
	}
	
//	http://localhost:9191/admin/createQuiz
	@PostMapping(value = "createQuiz", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String createQuiz(@RequestBody Quiz quiz) {
		return quizesService.addQuiz(quiz);
	}
	
	
//	http://localhost:9191/admin/checkUserResult
	@GetMapping(value = "checkUserResult", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Result> checkResult(){
		return resultService.getAllResult();
	}
	
	
	
}