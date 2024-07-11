package com.piseth.java.school.spring_webflux_demo.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.piseth.java.school.spring_webflux_demo.dto.ResponseDTO;
import com.piseth.java.school.spring_webflux_demo.util.SleepUtil;

@Service
public class MathService {
	
	
	// Create respond DTO : 10-07-2024 : 25
	public ResponseDTO findSquare(int input) {
		return new ResponseDTO(input*input);
	}
	
	// list of object table: 10-07-2024 : 5 ;10-07-2024 : 10
	// List is have unite index and element is change 
	public List<ResponseDTO> multiplicationTable(int input){
		// the same as loop from 1 to 10
		return IntStream.rangeClosed(1, 10) 
			.peek(x -> SleepUtil.sleepSecond(1)) // we use peek only for debug 
			.peek(x -> System.out.println("Math Service is processing.." + x))
			.map(x -> x * input)
			.mapToObj(x -> new ResponseDTO(x))
//			.collect(Collectors.toList()); in JAVA 11
			.toList();
			
	}


}
