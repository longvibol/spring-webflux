package com.piseth.java.school.webflux_playgroud.sec003.mapping;

import org.springframework.stereotype.Component;

import com.piseth.java.school.webflux_playgroud.sec003.dto.CustomerDTO;
import com.piseth.java.school.webflux_playgroud.sec003.entity.Customer;

@Component
//This class CustomerMapper = CustomerDTO what we want to show to outside 
public class CustomerMapping {

	// toCustomer is the method convert from CustomerDTO to customer  
	public Customer toCustomer(CustomerDTO dto) {
		Customer customer = new Customer();
		customer.setName(dto.getName());
		customer.setEmail(dto.getEmail());		
		return customer;
	}
	
	
	//toCustomerDTO is the method convert from Customer to CustomerDTO
	public CustomerDTO toCustomerDTO(Customer customer) {
		
		CustomerDTO dto = new CustomerDTO();
		dto.setId(customer.getId());
		dto.setName(customer.getName());
		dto.setEmail(customer.getEmail());
		
		return dto;
		
	}
	
	
}
