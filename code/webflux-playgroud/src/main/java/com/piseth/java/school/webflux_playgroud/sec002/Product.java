package com.piseth.java.school.webflux_playgroud.sec002;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table(name ="product")
public class Product {
	
	@Id
	private int id;
	private String description;
	private int price;

}
