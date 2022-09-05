package com.example.quizportal.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quizportal.entity.Question;

public interface QuestionRepository extends JpaRepository<Question,Integer> {

}
