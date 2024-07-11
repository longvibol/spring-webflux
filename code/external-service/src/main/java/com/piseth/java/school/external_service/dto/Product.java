package com.piseth.java.school.external_service.dto;

public record Product(int id, String description, int price) {
	
	// when we build as record it call Immunibale (it create class for us that can not change 
	// and the record also generate Getter and Setter for us 
	

}
