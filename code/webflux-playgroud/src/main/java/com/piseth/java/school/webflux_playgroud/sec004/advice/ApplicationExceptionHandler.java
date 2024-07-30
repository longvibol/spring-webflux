package com.piseth.java.school.webflux_playgroud.sec004.advice;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.piseth.java.school.webflux_playgroud.sec004.exception.CustomerNotFoundException;
import com.piseth.java.school.webflux_playgroud.sec004.exception.InvalidInputException;

@ControllerAdvice
public class ApplicationExceptionHandler {

	// inside spring-boot we have the problemDeatil

	@ExceptionHandler(InvalidInputException.class) // which exception we want to control
	public ProblemDetail invalidInputExceptionHandler(InvalidInputException ex) {
		ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
		problem.setTitle("Invalid Input");
		problem.setType(URI.create("http://example.com/problem.invalid-input"));
		return problem;
	}
	
	
	@ExceptionHandler(CustomerNotFoundException.class) // which exception we want to control
	public ProblemDetail customerNotFoundExceptionHandler(CustomerNotFoundException ex) {
		ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
		problem.setTitle("Customer Not Found!");
		problem.setType(URI.create("http://example.com/problem/customer-not-found"));
		return problem;
	}

}
