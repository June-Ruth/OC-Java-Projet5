package com.safetynet.safetynetalerts.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    /**
     * Exception when an element is not found.
     * @param s error message
     */
    public NotFoundException(final String s) {
        super(s);
    }
}
