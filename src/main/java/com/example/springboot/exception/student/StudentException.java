package com.example.springboot.exception.student;

public class StudentException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String ERROR_MESSAGE = "CreateStudentException: ";
	
	public StudentException (String msg) {
		super(ERROR_MESSAGE + msg);
	}
	
	public StudentException (String msg, Throwable throwable) {
		super(ERROR_MESSAGE + msg, throwable);
	}
}

