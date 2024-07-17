package com.piseth.java.school.webflux_playgroud.sec003.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.piseth.java.school.webflux_playgroud.sec003.entity.Customer;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer>{

}
