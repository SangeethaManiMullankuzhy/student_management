package com.receipt.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.library.exception.StudentNotFoundException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
    @ExceptionHandler(value = StudentNotFoundException.class)
    public ResponseEntity<Object> handleException(StudentNotFoundException ex) {
		Map<String, Object> response = new HashMap<>(); 
		response.put("status",HttpStatus.NOT_FOUND.value()); 
		response.put("error", "Not Found");
		response.put("message", ex.getMessage()); 
    	return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
    }

}
