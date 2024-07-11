package com.piseth.java.school.spring_webflux_demo.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ResponseDTO {
	
	private LocalDate date;
	private int result;
	
	public ResponseDTO(int result) {
		this.result=result;
		this.date = LocalDate.now();
	}

}
