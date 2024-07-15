package com.piseth.java.school.webflux_playgroud.sec002;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"sec=sec002","logging.level.org.springframework.r2dbc=DEBUG"})
public class AbstractTest {
	
	// we don't want to write it many time that why we use this abstractest and other class can extend from it 

}
