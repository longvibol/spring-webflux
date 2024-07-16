package com.piseth.java.school.webflux_playgroud.sec002.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.piseth.java.school.webflux_playgroud.sec002.CustomerOrder;
import com.piseth.java.school.webflux_playgroud.sec002.Product;
import com.piseth.java.school.webflux_playgroud.sec002.dto.OrderDetail;

import reactor.core.publisher.Flux;

public interface CustomerOrderRepository extends ReactiveCrudRepository<CustomerOrder, String> {
	
	//want to get all products which ordered by one customer name 
	
	@Query("""
			SELECT p.* 
			FROM product as p 
			INNER JOIN customer_order as co ON p.id = co.product_id
			INNER JOIN customer as c ON c.id = co.customer_id	
			WHERE c.name = :customerName
			""")
	// at the end we will get table name p =product (and that why we use Flux<Product> return 
	
	Flux<Product> findAllProductsByCustomerName(String customerName);
	
	
	@Query("""
			SELECT 
			co.order_id as order_id, 
			c.name as customer_name,
			p.description as product_name,
			co.amount as price,
			co.order_date as order_date
			FROM product as p 
			INNER JOIN customer_order as co ON p.id = co.product_id
			INNER JOIN customer as c ON c.id = co.customer_id
			WHERE p.description = :productName
			""")
	Flux<OrderDetail> findOrderDetailByProductName(String productName);
	
	
	
	
	
	
	
	
	
	
	
	
	

}
