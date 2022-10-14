package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserRegistration;
@Repository
public interface UserRegisrepo extends JpaRepository<UserRegistration,Integer> {

}
