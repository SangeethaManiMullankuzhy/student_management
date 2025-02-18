package com.fee.exceptions;

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
    
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleInvalidJson(HttpMessageNotReadableException ex, WebRequest request) {
        Throwable cause = ex.getCause();
        String message = "Invalid JSON request";

        if (cause instanceof JsonParseException) {
            message = "Malformed JSON: " + cause.getMessage();
        } else if (cause instanceof InvalidFormatException) {
            message = "Invalid date format";
        }
		Map<String, Object> response = new HashMap<>(); 
		response.put("status",HttpStatus.BAD_REQUEST.value()); 
		response.put("error", "Bad Request");
		response.put("message", message); 
		 

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
