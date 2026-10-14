package com.HackathonSubmissionGatewayProject.HackathonSubmissionGatewayProject.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) { 
        super(message); 
    }
}