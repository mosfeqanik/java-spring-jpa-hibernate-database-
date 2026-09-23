package com.PayGuardTransactionAPI.Project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Return HTTP 409 when this exception is thrown
@ResponseStatus(HttpStatus.CONFLICT)
public class SelfTransferException extends RuntimeException {

    // Constructor that accepts a custom error message
    public SelfTransferException(String message) {
        super(message);
    }
}