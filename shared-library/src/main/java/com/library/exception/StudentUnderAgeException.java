package com.library.exception;

public class StudentUnderAgeException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    public StudentUnderAgeException(String msg) {
    	super(msg);
    }


}
