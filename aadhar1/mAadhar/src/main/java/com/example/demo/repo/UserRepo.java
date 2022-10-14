package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;
@Repository
public interface UserRepo extends JpaRepository<User,Integer>{
	@Transactional
	@Modifying
    @Query("update User u set u.action=1 where u.id=:id")
	int approve(int id);
    
	
    
}
