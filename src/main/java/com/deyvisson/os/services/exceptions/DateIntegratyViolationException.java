package com.deyvisson.os.services.exceptions;

public class DateIntegratyViolationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DateIntegratyViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DateIntegratyViolationException(String message) {
		super(message);
	}

}
