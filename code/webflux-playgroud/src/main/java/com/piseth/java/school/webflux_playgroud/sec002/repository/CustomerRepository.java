package com.piseth.java.school.webflux_playgroud.sec002.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.piseth.java.school.webflux_playgroud.sec002.Customer;

import reactor.core.publisher.Flux;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {

	Flux<Customer> findByName(String name);

	Flux<Customer> findByNameStartingWith(String name);
}
