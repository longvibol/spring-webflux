package com.piseth.java.school.webflux_playgroud.sec003.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.piseth.java.school.webflux_playgroud.sec003.entity.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer>{

	
	@Modifying
	@Query("DELETE FROM customer WHERE id= :id")
	Mono<Boolean> deleteCustomerById(Integer id);
	
	Flux<Customer> findBy(Pageable pageable);
	
}
