package com.hackathonSubmissionGatewayProject.exception;

import com.hackathonSubmissionGatewayProject.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

/**
 * THE GLOBAL EXCEPTION HANDLER
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * HANDLER 1: Invalid Submissions
     */
    @ExceptionHandler(InvalidSubmissionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequest(InvalidSubmissionException ex) {
        return new ErrorResponse(
                400,
                "Bad Request",
                ex.getMessage(),
                LocalDateTime.now()
        );
    }

    /**
     * HANDLER 2: Submission After Deadline
     */
    @ExceptionHandler(SubmissionClosedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleClosed(SubmissionClosedException ex) {
        return new ErrorResponse(
                403,
                "Forbidden",
                ex.getMessage(),
                LocalDateTime.now()
        );
    }

    /**
     * HANDLER 3: Resource Not Found
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(ResourceNotFoundException ex) {
        return new ErrorResponse(
                404,
                "Not Found",
                ex.getMessage(),
                LocalDateTime.now()
        );
    }

    /**
     * HANDLER 4: Duplicate Submission
     */
    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleConflict(ConflictException ex) {
        return new ErrorResponse(
                409,
                "Conflict",
                ex.getMessage(),
                LocalDateTime.now()
        );
    }

    /**
     * THE SAFETY NET: Unexpected Errors
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGlobalException(Exception ex) {
        return new ErrorResponse(
                500,
                "Internal Server Error",
                "An unexpected error occurred.",
                LocalDateTime.now()
        );
    }
}