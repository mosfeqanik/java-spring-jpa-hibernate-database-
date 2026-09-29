package com.hackathonSubmissionGatewayProject.exception;

public class SubmissionClosedException extends RuntimeException {
    public SubmissionClosedException(String message) { 
        super(message); 
    }
}