package com.student.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.library.exception.StudentNotFoundException;
import com.library.exception.StudentUnderAgeException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class GlobalExceptionHandler {
	
    @ExceptionHandler(value = StudentNotFoundException.class)
    public ResponseEntity<Object> handleException(StudentNotFoundException ex) {
    	return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(value = StudentUnderAgeException.class)
    public ResponseEntity<Object> handleException(StudentUnderAgeException ex) {
    	return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
