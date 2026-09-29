package com.hackathonSubmissionGatewayProject.dto;

import java.time.LocalDateTime;

// Define as a Java record
public record ErrorResponse(

        // HTTP status code
        int status,

        // Short error title
        String error,

        // Detailed custom message
        String message,

        // Time of the error
        LocalDateTime timestamp

) {
    // No body needed
}