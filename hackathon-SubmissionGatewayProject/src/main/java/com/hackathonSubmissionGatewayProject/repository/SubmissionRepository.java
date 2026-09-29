package com.hackathonSubmissionGatewayProject.repository;

import com.hackathonSubmissionGatewayProject.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository interface extending JpaRepository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    /**
     * Checks whether a team name already exists, ignoring case.
     */
    boolean existsByTeamNameIgnoreCase(String teamName);

}