package com.piseth.java.school.webflux_playgroud.sec004.Controller;

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

import com.piseth.java.school.webflux_playgroud.sec004.dto.CustomerDTO;
import com.piseth.java.school.webflux_playgroud.sec004.exception.ApplicationException;
import com.piseth.java.school.webflux_playgroud.sec004.service.CustomerService;
import com.piseth.java.school.webflux_playgroud.sec004.validator.RequestValidator;

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
	Mono<CustomerDTO> getById(@PathVariable Integer customerId){
		return customerService.getById(customerId)
				.switchIfEmpty(ApplicationException.CustomerNotFoundException(customerId));
				
	}
	
	@PostMapping
	Mono<CustomerDTO> saveCustomer(@RequestBody Mono<CustomerDTO> mono){
		
		return mono.transform(RequestValidator.vaidate())
				.as(customerService::saveCustomer);
	}
	
	@PutMapping("{customerId}")
	Mono<CustomerDTO> updateCustomer(@PathVariable Integer customerId, @RequestBody Mono<CustomerDTO> mono){
		return mono.transform(RequestValidator.vaidate())
				.as(validateReq-> customerService.updateCustomer(customerId, validateReq))				
				.switchIfEmpty(ApplicationException.CustomerNotFoundException(customerId));
	}
	
	
	@DeleteMapping("{customerId}")
	Mono<Void> deleteById(@PathVariable Integer customerId){
		return customerService.deleteCustomerById(customerId)
				.filter(b -> b)
				.switchIfEmpty(ApplicationException.CustomerNotFoundException(customerId))
				.then();
	}
	
	

}
