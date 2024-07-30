package com.piseth.java.school.webflux_playgroud.sec004.exception;

public class InvalidInputException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5235254122427307696L;

	public InvalidInputException(String message) {
		super(message);
	}

}
