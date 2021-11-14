package com.grupodl.services.exceptions;

public class FieldInvalidException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public FieldInvalidException(String message) {
		super(message);
	}

}
