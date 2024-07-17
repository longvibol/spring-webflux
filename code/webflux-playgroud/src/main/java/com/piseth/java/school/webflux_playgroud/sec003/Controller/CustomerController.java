package com.piseth.java.school.webflux_playgroud.sec003.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.java.school.webflux_playgroud.sec003.dto.CustomerDTO;
import com.piseth.java.school.webflux_playgroud.sec003.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("customers")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping
	Flux<CustomerDTO> getAll(){
		return customerService.getAll();
	}
	
	@GetMapping("{customerId}")
	Mono<CustomerDTO> getById(@PathVariable Integer customerId){
		return customerService.getById(customerId);
	}
	
	@PostMapping
	Mono<CustomerDTO> saveCustomer(@RequestBody Mono<CustomerDTO> mono){
		return customerService.saveCustomer(mono);
	}
	
	@PutMapping("{customerId}")
	Mono<CustomerDTO> updateCustomer(@PathVariable Integer customerId, @RequestBody Mono<CustomerDTO> mono){
		return customerService.updateCustomer(customerId, mono);
	}
	
	
	@DeleteMapping("{customerId}")
	Mono<Void> deleteCustomerById(@PathVariable Integer customerId){
		return customerService.deleteCustomerById(customerId);
	}
	
	

}
