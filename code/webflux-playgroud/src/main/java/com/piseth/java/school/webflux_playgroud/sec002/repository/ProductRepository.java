package com.piseth.java.school.webflux_playgroud.sec002.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.piseth.java.school.webflux_playgroud.sec002.Product;

import reactor.core.publisher.Flux;


//@Repository = new version we no need to put Repository to create been 
@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Integer>{

	// we search in sprint method qurey then we follow the style 	
	Flux<Product> findByPriceBetween(int start, int end);
	
	
	Flux<Product> findBy(Pageable pageable); // findAllBy()
	
}
