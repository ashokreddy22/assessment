package com.example.quizportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan (basePackages = "com.example.quizportal.entity")
@EnableJpaRepositories (basePackages = "com.example.quizportal.Repository")
public class OnlineQuizPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineQuizPortalApplication.class, args);
	}

}
