package com.ITHelpdeskTicketResolverProject.ITHelpdeskTicketResolverProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Automatically return HTTP 409 CONFLICT
@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidTicketStateException extends RuntimeException {

    // Constructor with custom error message
    public InvalidTicketStateException(String message) {
        super(message);
    }
}