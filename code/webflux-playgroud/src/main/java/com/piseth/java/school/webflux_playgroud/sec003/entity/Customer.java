package com.piseth.java.school.webflux_playgroud.sec003.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table(name = "customer")
public class Customer {
	@Id
	private int id;
	private String name;
	private String email;
}
