package com.PayGuardTransactionAPI.Project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Return HTTP 422 when this exception is thrown
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InsufficientBalanceException extends RuntimeException {

    // Constructor with custom error message
    public InsufficientBalanceException(String message) {
        super(message);
    }
}