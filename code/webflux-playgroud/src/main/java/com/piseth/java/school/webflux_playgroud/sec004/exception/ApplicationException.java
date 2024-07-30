package com.piseth.java.school.webflux_playgroud.sec004.exception;

import reactor.core.publisher.Mono;

public class ApplicationException {

	
	// we use generice becaue can working with all the class 
	
	// validate on can not find customer Id when we use GET
	public static <T> Mono<T> CustomerNotFoundException(int id){
		return Mono.error(new CustomerNotFoundException(id));
	}
	
	public static <T> Mono<T> missingName(){
		return Mono.error(new InvalidInputException("Name is required"));
	}
	
	public static <T> Mono<T> missingValidEmail(){
		return Mono.error(new InvalidInputException("Valid is required"));
	}	
	
}
