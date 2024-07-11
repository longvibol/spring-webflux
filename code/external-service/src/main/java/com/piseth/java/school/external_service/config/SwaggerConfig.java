package com.piseth.java.school.external_service.config;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;

public class SwaggerConfig {
	
	@Bean
	  public OpenAPI myOpenAPI() {
	    Contact contact = new Contact();
	    contact.setName("Piseth Java School");
	    contact.setUrl("https://www.facebook.com/pisethjs/");
	    Info info = (new Info()).title("Spring WebFlux - Reactive Microservices")
	    		.version("1.0")
	    		.contact(contact)
	    		.description("This application exposes APIs for learning spring webflux.");
	    return (new OpenAPI()).info(info)
	      .tags(List.of((new Tag())
	           
	          .name("demo001")))
	      
	      .servers(Collections.emptyList());
	  }
	  
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler(new String[] { "/swagger-ui.html**" })
	    	.addResourceLocations(new String[] { "classpath:/resources/swagger-ui.html" });
	  }
				
				
				
				
				
		
	

}
