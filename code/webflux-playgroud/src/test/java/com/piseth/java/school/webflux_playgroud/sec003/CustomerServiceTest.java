package com.piseth.java.school.webflux_playgroud.sec003;

import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.piseth.java.school.webflux_playgroud.sec003.dto.CustomerDTO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@AutoConfigureWebTestClient
@SpringBootTest(properties = "sec=sec003")
public class CustomerServiceTest {
	
	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	void getCustomers() {
		webTestClient.get()
			.uri("/customers")
			.exchange() // we submit our test 
			.expectStatus().is2xxSuccessful() // status start with 2xx 
			.expectHeader().contentType(MediaType.APPLICATION_JSON) // we test on header return JSON type 
			.expectBodyList(CustomerDTO.class) // test on return Class in body : CustomerDTO
			.value(list -> log.info("{}",list))
			.hasSize(10); // the boday have 10 list of item 
	}
	
	
	@Test
	void getCustomersPagination() {
		webTestClient.get()
			.uri("/customers/paginated?number=3&size=2")
			.exchange()
			.expectStatus().is2xxSuccessful()
			.expectBody()
			.consumeWith(res -> log.info("{}", new String(res.getResponseBody())))
			.jsonPath("$.length()").isEqualTo(2)
			.jsonPath("$[0].id").isEqualTo(5)
			.jsonPath("$[1].id").isEqualTo(6);	
	
	}
	
	@Test
	void getCustomersById() {
		webTestClient.get()
			.uri("/customers/1")
			.exchange()
			.expectStatus().is2xxSuccessful()
			.expectBody()
			.consumeWith(res -> log.info("{}", new String(Objects.requireNonNull(res.getResponseBody()))))
			.jsonPath("$.id").isEqualTo(1)
			.jsonPath("$.name").isEqualTo("sam")
			.jsonPath("$.email").isEqualTo("sam@gmail.com");		
	}
	@Test
	void createAndDeleteCustomer() {
		//create 
		var dto = new CustomerDTO();
		dto.setName("vibol");
		dto.setEmail("vibol@gmail.com");
		
		webTestClient.post()
			.uri("/customers")
			.bodyValue(dto)
			.exchange()
			.expectStatus().is2xxSuccessful()
			.expectBody()
			.consumeWith(res -> log.info("{}", new String(Objects.requireNonNull(res.getResponseBody()))))
			.jsonPath("$.id").isEqualTo(11)
			.jsonPath("$.name").isEqualTo("vibol")
			.jsonPath("$.email").isEqualTo("vibol@gmail.com");		
		
		// Test Delete
		webTestClient.delete()
			.uri("/customers/11")
			.exchange()
			.expectStatus().is2xxSuccessful()
			.expectBody().isEmpty();
		
		
	}	
	
	@Test
	void updateCustomer() {
		var dto = new CustomerDTO();
		dto.setName("ethan");
		dto.setEmail("vibol@example.com");
		
		webTestClient.put()
			.uri("/customers/10")
			.bodyValue(dto)
			.exchange()
			.expectStatus().is2xxSuccessful()
			.expectBody()
			.consumeWith(res -> log.info("{}", new String(Objects.requireNonNull(res.getResponseBody()))))
			.jsonPath("$.id").isEqualTo(10)
			.jsonPath("$.name").isEqualTo("ethan")
			.jsonPath("$.email").isEqualTo("vibol@example.com");				
	}
	

	@Test
	void customerNotFound() {
		//get
		webTestClient.get()
			.uri("/customers/11")
			.exchange()
			.expectStatus().is4xxClientError()
			.expectBody().isEmpty();
		
		// delete
		webTestClient.delete()
			.uri("/customers/11")
			.exchange()
			.expectStatus().is4xxClientError()
			.expectBody().isEmpty();
		
		// update
		var dto = new CustomerDTO();
		dto.setName("ethan");
		dto.setEmail("ethan@gmail.com");
		webTestClient.put()
			.uri("/customers/11")
			.bodyValue(dto)
			.exchange()
			.expectStatus().is4xxClientError()
			.expectBody().isEmpty();
			
	}
	

}
