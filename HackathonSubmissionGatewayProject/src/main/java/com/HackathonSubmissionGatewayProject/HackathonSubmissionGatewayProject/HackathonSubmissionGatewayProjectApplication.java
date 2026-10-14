package com.HackathonSubmissionGatewayProject.HackathonSubmissionGatewayProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.HackathonSubmissionGatewayProject.HackathonSubmissionGatewayProject.repository")
public class HackathonSubmissionGatewayProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackathonSubmissionGatewayProjectApplication.class, args);
	}

}
