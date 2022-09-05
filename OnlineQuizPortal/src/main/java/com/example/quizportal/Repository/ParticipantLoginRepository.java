package com.example.quizportal.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quizportal.entity.Participant;

public interface ParticipantLoginRepository extends JpaRepository<Participant,Integer> {

	Optional<Participant> findByEmailid(String emailid);

}
