package com.example.quizportal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quizportal.Repository.QuestionRepository;
import com.example.quizportal.Repository.QuizRepository;
import com.example.quizportal.entity.Question;
import com.example.quizportal.entity.Quiz;

@Service
public class QuizService {
	@Autowired
	QuizRepository quizesRepository;
	@Autowired
	QuestionRepository questionRepository;
	
	public String addQuiz(Quiz quiz) {
		Optional<Quiz> op = quizesRepository.findById(quiz.getQzid());
		if(op.isPresent()) {
			return "Quiz id must be unique";
		}else {
			quizesRepository.save(quiz);
			return "Quiz added succesfully";
		}
	}
	
	
	public String findQuiz(Quiz quiz) {
		Optional<Quiz> op = quizesRepository.findById(quiz.getQzid());
		if(op.isPresent()) {
			Quiz q = op.get();
			return q.toString();
		}else {
			return "There is no quiz for this quiz Id";
		}
	}
	
	public List<Quiz> getAllQuizes(){
		return quizesRepository.findAll();
	}

}