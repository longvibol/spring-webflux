package com.piseth.java.school.webflux_playgroud.controller;

import java.util.List;

import javax.print.attribute.standard.Media;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

import com.piseth.java.school.webflux_playgroud.dto.Product;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("reactive")
@Slf4j
public class ReactiveWebController {
	
	private final WebClient webClient = WebClient
			.builder()
			.baseUrl("http://localhost:8080")
			.build();
	
	//webClient is the severice that get the respond from other service Flux
	
	@GetMapping("products")
	public Flux<Product> getProducts(){
		
		//java version 10 up we can use var = List<Product>
//		List<Product> list = this.restClient.get()		
		
	return this.webClient.get()
			.uri("/demo001/products")
			.retrieve()
			.bodyToFlux(Product.class)
			.doOnNext(x -> log.info("Received: " + x))
			;
			
	}
	
	@GetMapping(value="products/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Product> getProductsStream(){
	return this.webClient.get()
			.uri("/demo001/products")
			.retrieve()
			.bodyToFlux(Product.class)
			.doOnNext(x -> log.info("Received: " + x))
			;
			
	}

}
