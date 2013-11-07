package com.pascal.triangle.exception;

/**
 * Represents an invalid http parameter error, for example a missing mandatory
 * parameter.
 * 
 * @author adarrivi
 * 
 */
public class InvalidHttpParameterException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidHttpParameterException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidHttpParameterException(String message) {
		super(message);
	}

}
