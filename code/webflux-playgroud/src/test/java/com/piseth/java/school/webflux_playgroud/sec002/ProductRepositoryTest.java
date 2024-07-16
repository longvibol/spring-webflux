package com.piseth.java.school.webflux_playgroud.sec002;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.piseth.java.school.webflux_playgroud.sec002.repository.ProductRepository;

import reactor.test.StepVerifier;

public class ProductRepositoryTest extends AbstractTest{
	@Autowired
	private ProductRepository productRepository;
	
	
	@Test
	void findProductByPriceBetween() {
		productRepository.findByPriceBetween(500, 1000)
			.as(StepVerifier::create)
			.assertNext(t -> assertEquals("iphone 20", t.getDescription()))
			.assertNext(t -> assertEquals("iphone 18", t.getDescription()))
			.assertNext(t -> assertEquals("ipad", t.getDescription()))
//			.expectNextCount(3)
			.expectComplete()
			.verify();
	}
	
	
	@Test
	void findByPagination() {
		
		PageRequest pageRequest = PageRequest.of(1, 3).withSort(Sort.by("price").ascending());
		
		productRepository.findBy(pageRequest)
		.as(StepVerifier::create)
		.assertNext(t -> assertEquals(400, t.getPrice()))
		.assertNext(t -> assertEquals(750, t.getPrice()))
		.assertNext(t -> assertEquals(800, t.getPrice()))
		.expectComplete()
		.verify();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
