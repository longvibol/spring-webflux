package com.piseth.java.school.webflux_playgroud.sec002;
import java.time.Instant;


public record CustomerOrder(String uuid, int customerId, int productId, int amount, Instant orderDate ) {

}
