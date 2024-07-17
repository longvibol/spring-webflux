package com.piseth.java.school.webflux_playgroud.sec003.dto;

import lombok.Data;


// We create this CustomerDTO becuae we want the middle object that can convert from input and output 
@Data
public class CustomerDTO {

	private int id;
	private String name;
	private String email;
}
