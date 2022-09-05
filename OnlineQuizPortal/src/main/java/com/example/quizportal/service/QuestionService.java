package com.example.quizportal.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quizportal.Repository.QuestionRepository;
import com.example.quizportal.entity.Question;


@Service
public class QuestionService {
	@Autowired
	QuestionRepository questionsRepository;
	
	public String storeQuestion(Question questions) {
		Optional<Question> op = questionsRepository.findById(questions.getQid());
		if(op.isPresent()) {
			return "Question id must be unique";
		}else {
			questionsRepository.save(questions);
			return "Question added successfully";
		}
	}
	
	
	public Question findQuestion(int qid) {
		Optional<Question> op = questionsRepository.findById(qid);
		if(op.isPresent()) {
			Question question = op.get();
			return question;
		}else {
			
			return null;
		}
	}

}
