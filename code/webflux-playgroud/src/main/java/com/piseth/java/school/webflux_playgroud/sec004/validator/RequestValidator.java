package com.piseth.java.school.webflux_playgroud.sec004.validator;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import com.piseth.java.school.webflux_playgroud.sec004.dto.CustomerDTO;
import com.piseth.java.school.webflux_playgroud.sec004.exception.ApplicationException;

import reactor.core.publisher.Mono;

public class RequestValidator {
	
	// we use " UnaryOperator " when the Input Type and Output Type is the same! 
	// UnaryOperator extends from Funtion <T,T> 
	
	// in our validation is Publiser Mono CustomerDTO 
	
	public static UnaryOperator<Mono<CustomerDTO>> vaidate(){
		
		return mono-> mono.filter(hasName())
					.switchIfEmpty(ApplicationException.missingName())
					.filter(hasValidEmail())
					.switchIfEmpty(ApplicationException.missingValidEmail()); 		
	}	
	
	// Predicate : anyinput type -> will return Booleand 
	private static Predicate<CustomerDTO> hasName(){
		return dto -> Objects.nonNull(dto.getName());
	}
	
	private static Predicate<CustomerDTO> hasValidEmail(){
		return dto -> Objects.nonNull(dto.getEmail()) && dto.getEmail().contains("@");
	}

}
