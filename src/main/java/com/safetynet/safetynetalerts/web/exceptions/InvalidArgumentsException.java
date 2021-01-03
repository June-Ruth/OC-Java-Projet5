package com.safetynet.safetynetalerts.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidArgumentsException extends RuntimeException {
    /**
     * Exception for invalid arguments in parameters.
     * @param s
     */
    public InvalidArgumentsException(final String s) {
        super(s);
    }
}
