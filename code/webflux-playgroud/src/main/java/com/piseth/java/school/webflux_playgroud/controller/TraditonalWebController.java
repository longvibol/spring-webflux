package com.piseth.java.school.webflux_playgroud.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.piseth.java.school.webflux_playgroud.dto.Product;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("traditional")
@Slf4j
public class TraditonalWebController {
	
	private final RestClient restClient = RestClient
			.builder()
			.baseUrl("http://localhost:8080")
			.build();	
	//RestClient is the severice that get the respond from other service 
	
	@GetMapping("products")
	public List<Product> getProducts(){
		
		//java version 10 up we can use var = List<Product>
//		List<Product> list = this.restClient.get()		
		
		var list = this.restClient.get()
//			.uri("/demo001/products")
			.uri("/demo001/products/black")
			.retrieve()
			.body(new ParameterizedTypeReference<List<Product>>() {
			});
		log.info("Recieved Response: {}" ,list);
		return list;
	}
	
	/*
	 * this is not Spring Webflux because they use List (synchrous blocking)
	 * 
	@GetMapping("products2")
	public Flux<Product> getProducts2(){
	
		var list = this.restClient.get()
			.uri("/demo001/products/black")
			.retrieve()
			.body(new ParameterizedTypeReference<List<Product>>() {
			});
		log.info("Recieved Response: {}" ,list);
		return Flux.fromIterable(list);
	}
	
	*/

}
