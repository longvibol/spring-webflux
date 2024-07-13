package com.piseth.java.school.external_service.service;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.piseth.java.school.external_service.dto.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
	
	public Flux<Product> getProduct(){
		return Flux.range(1, 10)
			.delayElements(Duration.ofSeconds(1))
			.map(x -> new Product(x, "Product_"+x, x));
	}
	
	public Flux<Product> getProductWithError(){
		return Flux.range(1, 10)
			.delayElements(Duration.ofSeconds(1))
			.take(4)
			.map(x -> new Product(x, "Product_"+x, x))
			.concatWith(Mono.fromRunnable(() -> System.exit(1)));
			// system.exit = when meet error end the process 
	}

}
