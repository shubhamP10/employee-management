package com.mphasis.employeemanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {
    private String message;
    private Date date;
    private String error;

}
