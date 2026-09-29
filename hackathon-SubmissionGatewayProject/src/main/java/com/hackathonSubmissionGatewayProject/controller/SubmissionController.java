package com.hackathonSubmissionGatewayProject.controller;

import com.hackathonSubmissionGatewayProject.exception.*;
import com.hackathonSubmissionGatewayProject.model.Submission;
import com.hackathonSubmissionGatewayProject.repository.SubmissionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Optional;

// Mark this class as a REST Controller
@RestController
// Map all endpoints to /api
@RequestMapping("/api")
public class SubmissionController {

    // Constructor injection
    private final SubmissionRepository submissionRepository;

    public SubmissionController(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    // The hackathon deadline
    private final LocalDateTime DEADLINE = LocalDateTime.of(2026, 3, 1, 23, 59);

    /*
     * ENDPOINT 1: Submit a Project
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Submission submitProject(@RequestBody Submission submission) {

        // 1. Deadline Enforcement
        if (LocalDateTime.now().isAfter(DEADLINE)) {
            throw new SubmissionClosedException(
                    "The hackathon deadline has passed. Submissions are closed."
            );
        }

        // 2. Data Validation
        if (submission.getGithubRepoUrl() == null ||
                !submission.getGithubRepoUrl().contains("github.com")) {
            throw new InvalidSubmissionException(
                    "A valid github.com repository URL is required."
            );
        }

        // 3. Duplicate Submission Block
        if (submissionRepository.existsByTeamNameIgnoreCase(submission.getTeamName())) {
            throw new ConflictException(
                    "Team '" + submission.getTeamName() +
                    "' has already submitted a project."
            );
        }

        // 4. Save to H2 Database
        return submissionRepository.save(submission);
    }

    /*
     * ENDPOINT 2: Get All Submissions
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Submission> getAllSubmissions() {

        return submissionRepository.findAll();
    }

    /*
     * ENDPOINT 3: Get a Specific Submission by ID
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Submission getSubmission(@PathVariable Long id) {

        Optional<Submission> result = submissionRepository.findById(id);

        if (result.isPresent()) {
            return result.get();
        } else {
            throw new ResourceNotFoundException(
                    "Submission with ID " + id + " was not found."
            );
        }
    }

    /*
     * ENDPOINT 4: Delete a Submission
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubmission(@PathVariable Long id) {

        Optional<Submission> result = submissionRepository.findById(id);

        if (result.isPresent()) {
            Submission existingSubmission = result.get();

            submissionRepository.delete(existingSubmission);
        } else {
            throw new ResourceNotFoundException(
                    "Submission with ID " + id + " was not found."
            );
        }
    }
}