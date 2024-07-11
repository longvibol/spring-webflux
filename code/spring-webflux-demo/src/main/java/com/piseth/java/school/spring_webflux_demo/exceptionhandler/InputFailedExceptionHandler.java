package com.piseth.java.school.spring_webflux_demo.exceptionhandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.piseth.java.school.spring_webflux_demo.dto.InputFailedResponseDTO;
import com.piseth.java.school.spring_webflux_demo.exception.InputFailedValidationException;

@ControllerAdvice
public class InputFailedExceptionHandler {

	@ExceptionHandler(InputFailedValidationException.class)
	public ResponseEntity<InputFailedResponseDTO> inputFailedhandler(InputFailedValidationException er){
		InputFailedResponseDTO response = new InputFailedResponseDTO();
		response.setErrorCode(er.getErrorCode());
		response.setInput(er.getInput());
		response.setMessage(er.getMessage());
		
		return ResponseEntity.badRequest().body(response);
	}
}
