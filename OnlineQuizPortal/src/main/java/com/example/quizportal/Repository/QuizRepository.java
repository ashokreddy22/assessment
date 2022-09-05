package com.example.quizportal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quizportal.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz,Integer> {

}
