package com.mphasis.employeemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotNull;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FieldAlreadyExistException extends RuntimeException {
    public FieldAlreadyExistException(String field, @NotNull String fieldValue) {
        super(String.format("%s: %s Already Exists!!!", field,fieldValue));
    }
}
