package com.example.quizportal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quizportal.entity.Login;

public interface LoginRepository extends JpaRepository<Login,String> {

}
