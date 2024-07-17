package com.piseth.java.school.webflux_playgroud.sec003.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piseth.java.school.webflux_playgroud.sec003.dto.CustomerDTO;
import com.piseth.java.school.webflux_playgroud.sec003.mapping.CustomerMapping;
import com.piseth.java.school.webflux_playgroud.sec003.repository.CustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	CustomerMapping customerMapping;
	
	public Flux<CustomerDTO> getAll(){
		return customerRepository.findAll()
//			.map(c -> customerMapping.toCustomerDTO(c));
			.map(customerMapping::toCustomerDTO);  // Method Reference : we call the call then implement the methode 
	}
	
	public Mono<CustomerDTO> getById(Integer customerId){
		return customerRepository.findById(customerId)
					.map(customerMapping::toCustomerDTO);
	}
	
	public Mono<CustomerDTO> saveCustomer(Mono<CustomerDTO> mono){
		//Save use POST Method from Rest : Neet to convert from outside(customerDTO to inside (customer-our entitiy) 
		return mono.map(c -> customerMapping.toCustomer(c))
			.flatMap(c -> customerRepository.save(c))
			.map(c -> customerMapping.toCustomerDTO(c));
			
	}
	
	public Mono<CustomerDTO> updateCustomer(Integer customerId, Mono<CustomerDTO> mono){
		return customerRepository.findById(customerId) // we support we get the entity from the DB
				.flatMap(entity -> mono) // we update the entity we get from DB to new request update from Input 
				// after that we get <Mono<CustomerDTO>
				.map(c -> customerMapping.toCustomer(c)) // we get Mono<Customer> 
		
				// we want to replace into DB but it do not have id yet 
				.doOnNext(c -> c.setId(customerId))  // style JPA: this extend ReativeCrudRepostitoy if it don't have ID it will create new ID
				.flatMap(c ->customerRepository.save(c))
				.map(c -> customerMapping.toCustomerDTO(c));				
				
	}
	
	public Mono<Void> deleteCustomerById(Integer customerId){
		return customerRepository.findById(customerId)
			.flatMap(c ->customerRepository.delete(c));
	}
	
	

}
