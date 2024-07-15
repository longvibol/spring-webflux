package com.piseth.java.school.webflux_playgroud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;


@ComponentScan(basePackages = "com.piseth.java.school.webflux_playgroud.${sec}")
@EnableR2dbcRepositories(basePackages = "com.piseth.java.school.webflux_playgroud.${sec}")
@SpringBootApplication
public class WebfluxPlaygroudApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxPlaygroudApplication.class, args);
	}

}
