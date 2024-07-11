package com.piseth.java.school.spring_webflux_demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.java.school.spring_webflux_demo.dto.MultiplyRequestDTO;
import com.piseth.java.school.spring_webflux_demo.dto.ResponseDTO;
import com.piseth.java.school.spring_webflux_demo.exception.InputFailedValidationException;
import com.piseth.java.school.spring_webflux_demo.service.ReactiveMathService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathController {
	
	
	@Autowired
	private ReactiveMathService mathService;
	
	@GetMapping("square/{input}")
	public Mono<ResponseDTO> findSquare(@PathVariable int input) {
		return mathService.findSquare(input);
	}
	
	@GetMapping("square/{input}/throw")
	public Mono<ResponseDTO> findSquareWithException(@PathVariable int input) {
		if (input < 10 || input > 20) {
			throw new InputFailedValidationException(input);
		}
		return mathService.findSquare(input);
	}
	
	// with control error inside pipe line 
	@GetMapping("square/{input}/assingment")
	public Mono<ResponseEntity<ResponseDTO>> findSquareWithExceptionErrorSignal(@PathVariable int input) {
		return Mono.just(input)
					.filter(x -> x > 10 && x < 20)
					.flatMap(x -> mathService.findSquare(x)) // flat the result to map 
					.map(x -> ResponseEntity.ok(x)) // get status respond 400 
					.defaultIfEmpty(ResponseEntity.badRequest().build()); // afte filter it will meet the case where don't have in rang 
		
	}
	
	@GetMapping("square/{input}/mono-error")
	public Mono<ResponseDTO> assignment(@PathVariable int input) {
		return mathService.findSquare(input);
	}
	
	
	/* MVC Style : 
	@GetMapping("table/{input}")
	public Flux<ResponseDTO> table(@PathVariable int input){
		return mathService.multiplicationTable(input);
	}
	*/
	
	@GetMapping(value = "table/{input}", produces = org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ResponseDTO> table(@PathVariable int input){
		return mathService.multiplicationTable(input);
	}
	
	@PostMapping("multiply")
	public Mono<ResponseDTO> multiply(@RequestBody Mono<MultiplyRequestDTO> dto,@RequestHeader Map<String, String> header){
		System.out.println(header);
		return mathService.multiply(dto);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
