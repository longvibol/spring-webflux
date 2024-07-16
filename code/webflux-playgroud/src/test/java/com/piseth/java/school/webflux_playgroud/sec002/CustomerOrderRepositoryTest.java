package com.piseth.java.school.webflux_playgroud.sec002;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.piseth.java.school.webflux_playgroud.sec002.repository.CustomerOrderRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.test.StepVerifier;

@Slf4j
public class CustomerOrderRepositoryTest extends AbstractTest{
	@Autowired
	private CustomerOrderRepository customerOrderRepository;
	
	
	@Test
	void findAllProductByCustomerName() {
		customerOrderRepository.findAllProductsByCustomerName("sam")
			.doOnNext(t -> log.info("Received: {}",t))
			.as(StepVerifier::create)
			.assertNext(t ->assertEquals("iphone 20", t.getDescription()))
			.assertNext(t ->assertEquals("iphone 18", t.getDescription()))		
//			.expectNextCount(2)
			.expectComplete()
			.verify();
	}
	
	@Test
	void findOrderDetailByProductName() {
		customerOrderRepository.findOrderDetailByProductName("iphone 20")
			.doOnNext(t -> log.info("Received: {}",t))
			.as(StepVerifier::create)
			.assertNext(t ->assertEquals("sam", t.customerName() ))
			.assertNext(t ->assertEquals("mike", t.customerName()))		
			//.expectNextCount(2)
			.expectComplete()
			.verify();
	}
	
	
	
	
}
