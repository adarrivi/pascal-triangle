package com.pascal.triangle.exception;

/**
 * Represents an invalid parameter given to solve the human pyramid problem, for
 * example a negative index.
 * 
 * @author adarrivi
 * 
 */
public class InvalidTriangleException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidTriangleException(String message) {
		super(message);
	}
}
