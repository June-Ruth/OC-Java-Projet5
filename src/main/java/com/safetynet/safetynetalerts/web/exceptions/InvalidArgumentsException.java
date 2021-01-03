package com.safetynet.safetynetalerts.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidArgumentsException extends RuntimeException {
    public InvalidArgumentsException(String s) {
        super(s);
    }
}
