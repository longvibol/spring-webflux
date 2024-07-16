package com.piseth.java.school.webflux_playgroud.sec002.dto;

import java.time.Instant;

public record OrderDetail(
		String orderId, 
		String customerName, 
		String productName, 
		Integer price,
		Instant orderDate) {

}
