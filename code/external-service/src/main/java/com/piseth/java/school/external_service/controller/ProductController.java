package com.piseth.java.school.external_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.java.school.external_service.dto.Product;
import com.piseth.java.school.external_service.service.ProductService;
import com.piseth.java.school.external_service.util.Transformer;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("demo001")
@RequiredArgsConstructor
public class ProductController {
	
	private ProductService productService;
	
	@GetMapping("products")
	@Operation(summary = "Product Service", description = "Produce up to 10 item, each item takes 1 second. So, "
			+ "10 products will take 10 seconds.", tags = {"demo001"})
	public Flux<Product> getProducts(){
		
		String path = "demo001/products";
		return productService.getProduct()
				.transform(Transformer.fluxLogger(path));
	}

}
