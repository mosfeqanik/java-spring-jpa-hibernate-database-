package com.hackathonSubmissionGatewayProject.model;

import jakarta.persistence.*;

// Mark this class as a JPA Entity
@Entity
// Specify the exact table name
@Table(name = "submissions")
public class Submission {

    // Primary Key
    @Id
    // Auto-Increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Cannot be null and must be unique
    @Column(nullable = false, unique = true)
    private String teamName;

    // Cannot be null
    @Column(nullable = false)
    private String projectTitle;

    // Cannot be null
    @Column(nullable = false)
    private String githubRepoUrl;

    // Default Constructor (required by JPA)
    public Submission() {

    }

    // Parameterized Constructor
    public Submission(String teamName, String projectTitle, String githubRepoUrl) {
        this.teamName = teamName;
        this.projectTitle = projectTitle;
        this.githubRepoUrl = githubRepoUrl;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getGithubRepoUrl() {
        return githubRepoUrl;
    }

    public void setGithubRepoUrl(String githubRepoUrl) {
        this.githubRepoUrl = githubRepoUrl;
    }
}