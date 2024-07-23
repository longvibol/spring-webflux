package com.piseth.java.school.webflux_playgroud.sec003.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("paginated")
	Flux<CustomerDTO> getCustomers(@RequestParam int number, @RequestParam int size){
		//customers/paginated?number=2&size=5		
		return customerService.getCustomers(number, size);
	}
	
	@GetMapping("paginated2")
	Mono<Page<CustomerDTO>> getCustomers2(@RequestParam int number, @RequestParam int size){
		return customerService.getCustomers2(number, size);
	}
	
	
	
	@GetMapping("{customerId}")
	Mono<ResponseEntity<CustomerDTO>> getById(@PathVariable Integer customerId){
		return customerService.getById(customerId)
				.map(dto -> ResponseEntity.ok(dto))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	Mono<CustomerDTO> saveCustomer(@RequestBody Mono<CustomerDTO> mono){
		return customerService.saveCustomer(mono);
	}
	
	@PutMapping("{customerId}")
	Mono<ResponseEntity<CustomerDTO>> updateCustomer(@PathVariable Integer customerId, @RequestBody Mono<CustomerDTO> mono){
		return customerService.updateCustomer(customerId, mono)
				.map(dto -> ResponseEntity.ok(dto))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	
	@DeleteMapping("{customerId}")
	Mono<ResponseEntity<Void>> deleteById(@PathVariable Integer customerId){
		return customerService.deleteCustomerById(customerId)
				.filter(b -> b)
				.map(dto -> ResponseEntity.ok().<Void>build())
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	

}
