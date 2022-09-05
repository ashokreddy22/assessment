package com.example.quizportal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quizportal.entity.Result;

public interface ResultRepository extends JpaRepository<Result,String> {

}
