package com.piseth.java.school.webflux_playgroud.sec002;
import java.time.Instant;

import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table(name = "customer_order")
public class CustomerOrder{
	private String uuid;
	private int customerId;
	private int productId;
	private int amount;
	private Instant orderDate; 
}
