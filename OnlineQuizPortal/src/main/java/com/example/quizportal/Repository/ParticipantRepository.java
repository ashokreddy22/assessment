package com.example.quizportal.Repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quizportal.entity.Participant;

public interface ParticipantRepository extends JpaRepository<Participant,Integer>{

}
