package com.pascal.triangle.model.exception;

public class InvalidTriangleException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidTriangleException(String message) {
		super(message);
	}
}
