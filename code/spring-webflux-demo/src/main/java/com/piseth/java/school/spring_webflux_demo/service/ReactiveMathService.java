package com.piseth.java.school.spring_webflux_demo.service;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import com.piseth.java.school.spring_webflux_demo.dto.MultiplyRequestDTO;
import com.piseth.java.school.spring_webflux_demo.dto.ResponseDTO;
import com.piseth.java.school.spring_webflux_demo.exception.InputFailedValidationException;
import com.piseth.java.school.spring_webflux_demo.util.SleepUtil;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class ReactiveMathService {

	// we try to build lazy as much as posible 	
	public Mono<ResponseDTO> findSquare(int input){
		
		return Mono.fromSupplier(() -> input*input)
					.map(x -> new ResponseDTO(x));
	}
	
	//with Mono Exception handler
	public Mono<ResponseDTO> findSquareWithException(int input){
		
		return Mono.fromSupplier(() -> input)
			.handle((number, sink) ->{
				if(number <10 || number >20) {
					sink.error(new InputFailedValidationException(input));
				}else {
					sink.next(new ResponseDTO(input));
				}
			});
	}
		
	public Flux<ResponseDTO> multiplicationTable(int input){
		return Flux.range(1, 10)
				//.doOnNext(x -> SleepUtil.sleepSecond(1))
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(x -> System.out.println("Reactive math Service is processing " +x))
				.map(x -> new ResponseDTO(x *x));
	}
	
	
	// It buil the data sturcture List so it is not the reactive Style (it is blocking) 
	public Flux<ResponseDTO> multiplicationTable2(int input){
		List<ResponseDTO> list = IntStream.rangeClosed(1, 10)
				.peek(x -> SleepUtil.sleepSecond(1)) 
				.peek(x -> System.out.println("Math Service is processing.." + x))
				.map(x -> x * input)
				.mapToObj(x -> new ResponseDTO(x))
				.toList();
		
		return Flux.fromIterable(list); // bad practice 
	}
	
	
	public Mono<ResponseDTO> multiply(Mono<MultiplyRequestDTO> multiplyMono){
		return multiplyMono
			.map(x -> new ResponseDTO(x.getFirst() *x.getSecond()));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
