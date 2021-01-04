package com.safetynet.safetynetalerts.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExistingException extends RuntimeException {
    /**
     * Exception when an element is already existing.
     * @param s
     */
    public AlreadyExistingException(final String s) {
        super(s);
    }
}
