package com.piseth.java.school.webflux_playgroud.sec004.exception;

public class CustomerNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1665399558961778181L;
	private static final String MESSAGE ="Customer [id = %d] is not found";
	
	// we want to use Message above and we don't want to call this meesage 
	// so we can create constractor then when it generatate it will call this MESSAGE 
	
	public CustomerNotFoundException(int custoemrId) {
		super(MESSAGE.formatted(custoemrId));
	}
	
	
	/*
	 *from runtimeExpection it have contracor String 
	 *
	 * public RuntimeException(String message) {
        super(message);

	 */

}
