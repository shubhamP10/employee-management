package com.mphasis.employeemanagement.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEmployeeNotFoundException(EmployeeNotFoundException ex, WebRequest request){
        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), new Date(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleDepartmentNotFoundException(DepartmentNotFoundException ex, WebRequest request){
        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), new Date(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FieldAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> handleFieldAlreadyExistsException(FieldAlreadyExistException ex, WebRequest request){
        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), new Date(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), new Date());
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder errorStringBuilder = new StringBuilder();
        bindingResult.getAllErrors()
                .forEach(objectError -> errorStringBuilder.append(objectError.getDefaultMessage()).append(", "));
        errorStringBuilder.replace(errorStringBuilder.lastIndexOf(", "), errorStringBuilder.length(), "");
        ExceptionResponse response = new ExceptionResponse("Validation Failed!!!", new Date(), errorStringBuilder.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }
}
