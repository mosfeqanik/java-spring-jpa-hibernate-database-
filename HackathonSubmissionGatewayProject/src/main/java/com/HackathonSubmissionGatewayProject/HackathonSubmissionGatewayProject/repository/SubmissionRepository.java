package com.HackathonSubmissionGatewayProject.HackathonSubmissionGatewayProject.repository;

import com.HackathonSubmissionGatewayProject.HackathonSubmissionGatewayProject.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    /**
     * Checks whether a team name already exists, ignoring case.
     */
    boolean existsByTeamNameIgnoreCase(String teamName);

}