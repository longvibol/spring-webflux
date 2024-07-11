package com.piseth.java.school.external_service.service;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.piseth.java.school.external_service.dto.Product;

import reactor.core.publisher.Flux;

@Service
public class ProductService {
	
	public Flux<Product> getProduct(){
		return Flux.range(1, 10)
			.delayElements(Duration.ofSeconds(1))
			.map(x -> new Product(x, "Product_"+x, x));
	}

}
